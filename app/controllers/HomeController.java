package controllers;

import play.api.Environment;
import play.mvc.*;

import views.html.*;
import play.data.*;

import java.util.*;

import javax.inject.Inject;

import models.users.*;
import models.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    private FormFactory formFactory;
    private Environment env;

    @Inject
    public HomeController(FormFactory f, Environment e) {
        this.formFactory = f;
        this.env = e;
    }

    public Result index() {
        return ok(index.render("Project is ready"));
    }

    public Result login() {
        Form<Login> loginForm = formFactory.form(Login.class);
        return ok(login.render(loginForm));
    }
    public Result store(){
        List<Items> allItems = Items.findAll();
        return ok(store.render(allItems, env));
    }
}
