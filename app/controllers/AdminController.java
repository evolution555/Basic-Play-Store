package controllers;

import com.avaje.ebeaninternal.server.lib.util.Str;
import play.api.Environment;
import play.db.ebean.Transactional;
import play.mvc.*;
import views.html.AdminPages.*;
import play.data.*;
import models.users.*;
import models.*;

import javax.inject.Inject;
import java.util.*;

import play.mvc.Http.MultipartFormData.FilePart;

import java.io.*;

@Security.Authenticated(Secured.class)
@With(AuthAdmin.class)
public class AdminController extends Controller {
    private FormFactory formFactory;
    private Environment env;

    @Inject
    public AdminController(FormFactory f, Environment e) {
        this.formFactory = f;
        this.env = e;
    }

    public Result adminHome() {
        User u = HomeController.getUserFromSession();
        return ok(adminHome.render(u, env));
    }

    public Result adminItems() {
        User u = HomeController.getUserFromSession();
        List<Items> allItems = Items.findAll();
        return ok(adminItems.render(u, env, allItems));
    }

    public Result adminAddItem() {
        Items i = new Items();
        User u = HomeController.getUserFromSession();
        return ok(adminAddItem.render(i, u, null));
    }

    public Result addItemSubmit() {
        DynamicForm df = formFactory.form().bindFromRequest();
        Items i = new Items();
        i.setTitle(df.get("title"));
        i.setDescription(df.get("description"));
        i.setCatagory(df.get("catagory"));
        try {
            i.setCost(Double.parseDouble(df.get("cost")));
        } catch (NumberFormatException e) {
            return badRequest(adminAddItem.render(i, HomeController.getUserFromSession(), "Cost must be a number"));
        }

        String saveImageMsg;

        List<Items> allItemss = Items.findAll();
        for (Items Items : allItemss) {
            if (Items.getTitle().equals(i.getTitle())) {
                return badRequest(adminAddItem.render(i, HomeController.getUserFromSession(), "Movie already in database."));
            }
        }
        i.save();

        Http.MultipartFormData data = request().body().asMultipartFormData();
        FilePart image = data.getFile("upload");

        flash("success", saveFile(i.getItemId(), image));
        return redirect(routes.AdminController.adminItems());
    }


    //Image Save
    public String saveFile(String title, FilePart<File> uploaded) {
        if (uploaded != null) {
            String filename = uploaded.getFilename();
            String extension = "";

            String mimeType = uploaded.getContentType();

            if (mimeType.startsWith("image/")) {
                int i = filename.lastIndexOf('.');
                if (i >= 0) {
                    extension = filename.substring(i + 1);
                }

                File file = uploaded.getFile();
                file.renameTo(new File("public/images/Items/" + title + "." + extension));
            }
            return "Item Added";
        }
        return "no file";
    }
}
