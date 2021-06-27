package edu.jd.xyt.lib;

import edu.jd.xyt.common.Result;
import edu.jd.xyt.common.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.TransformService;
import java.io.IOException;
import java.util.List;

@WebServlet("/lib/libList")
public class LibAPI extends HttpServlet {
    protected void doPost ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }

    protected void doGet ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        System.out.println("historyy!");
        LibService libService = new LibService();
        List<Library> libList = libService.getLibraryList();


        Utils.outResult(response, Result.success(libList));
    }
}