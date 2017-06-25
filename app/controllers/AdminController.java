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

    public Result adminItems(){
        User u = HomeController.getUserFromSession();
        return ok(adminItems.render(u, env));
    }

}
