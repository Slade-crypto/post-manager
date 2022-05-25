package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comprador;
import model.ModelException;
import model.Post;
import model.User;
import model.dao.CompradorDAO;
import model.dao.DAOFactory;
import model.dao.PostDAO;
import model.dao.UserDAO;


@WebServlet(urlPatterns = {"/compradores", "/compradores/form", "/compradores/delete", "/compradores/insert", "/compradores/update"})
public class CompradorController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		
		switch (action) {
		case "/post-manager/compradores/form": {
			listUsers(req);
			req.setAttribute("action", "insert");
			ControllerUtil.forward(req, resp, "/form-comprador.jsp");
			break;
		}
		case "/post-manager/compradores/update": {
			listUsers(req);
			Comprador comprador = loadComprador(req);
			req.setAttribute("comprador", comprador);
			req.setAttribute("action", "update");
			ControllerUtil.forward(req, resp, "/form-comprador.jsp");
			break;
		}
		default:
			listCompradores(req);
			
			ControllerUtil.transferSessionMessagesToRequest(req);
		
			ControllerUtil.forward(req, resp, "/compradores.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		
		if (action == null || action.equals("") ) {
			ControllerUtil.forward(req, resp, "/index.jsp");
			return;
		}
		
		switch (action) {
		case "/post-manager/compradores/delete":
			deleteComprador(req, resp);
			break;	
		case "/post-manager/compradores/insert": {
			insertComprador(req, resp);
			break;
		}
		case "/post-manager/compradores/update": {
			updateComprador(req, resp);
			break;
		}
		default:
			System.out.println("URL invalida " + action);
			break;
		}
			
		ControllerUtil.redirect(resp, req.getContextPath() + "/compradores");
	}

	private Comprador loadComprador(HttpServletRequest req) {
		String compradorIdParameter = req.getParameter("compradorId");
		
		int compradorId = Integer.parseInt(compradorIdParameter);
		
		CompradorDAO dao = DAOFactory.createDAO(CompradorDAO.class);
		
		try {
			Comprador comprador = dao.findById(compradorId);
			
			if (comprador == null)
				throw new ModelException("Usuario não encontrado para Alteração");
			
			return comprador;
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
		
		return null;
	}
	
	private void listCompradores(HttpServletRequest req) {
		CompradorDAO dao = DAOFactory.createDAO(CompradorDAO.class);
		
		List<Comprador> compradores = null;
		try {
			compradores = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
		
		if (compradores != null)
			req.setAttribute("compradores", compradores);
	}
	
	private void insertComprador(HttpServletRequest req, HttpServletResponse resp) {
		String compradorName = req.getParameter("name");
		String compradorAddress = req.getParameter("address");
		String compradorEmail = req.getParameter("mail");
		String userId = req.getParameter("user_id");
		
		Comprador comprador = new Comprador();
		comprador.setName(compradorName);
		comprador.setAddress(compradorAddress);
		comprador.setEmail(compradorEmail);
		comprador.setUser(new User(Integer.parseInt(userId)));
		
		CompradorDAO dao = DAOFactory.createDAO(CompradorDAO.class);
		
		try {
			if (dao.save(comprador)) {
				ControllerUtil.sucessMessage(req, "Usuario '" + comprador.getName() + "' salvo com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Usuario '" + comprador.getName() + "' não pode ser salvo.");
			}
				
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
	
	private void updateComprador(HttpServletRequest req, HttpServletResponse resp) {
		String compradorName = req.getParameter("name");
		String compradorAddress = req.getParameter("address");
		String compradorEmail = req.getParameter("mail");
		
		Comprador comprador = loadComprador(req);
		comprador.setName(compradorName);
		comprador.setAddress(compradorAddress);
		comprador.setEmail(compradorEmail);
		
		CompradorDAO dao = DAOFactory.createDAO(CompradorDAO.class);
		
		try {
			if (dao.update(comprador)) {
				ControllerUtil.sucessMessage(req, "Usuario '" + comprador.getName() + "' atualizado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Usuario '" + comprador.getName() + "' não pode ser atualizado.");
			}
				
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
	
	private void deleteComprador(HttpServletRequest req, HttpServletResponse resp) {
		String compradorIdParameter = req.getParameter("id");
		
		int compradorId = Integer.parseInt(compradorIdParameter);
		
		CompradorDAO dao = DAOFactory.createDAO(CompradorDAO.class);
		
		try {
			Comprador comprador = dao.findById(compradorId);
			
			if (comprador == null)
				throw new ModelException("Usuario não encontrado para deleção.");
			
			if (dao.delete(comprador)) {
				ControllerUtil.sucessMessage(req, "Usuario '" + comprador.getName() + "' deletado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Usuario '" + comprador.getName() + "' não pode ser deletado.");
			}
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
	public static void listUsers(HttpServletRequest req) {
		UserDAO dao = DAOFactory.createDAO(UserDAO.class);
		
		List<User> listUsers = null;
		try {
			listUsers = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
		
		if (listUsers != null)
			req.setAttribute("users", listUsers);		
	}
}
