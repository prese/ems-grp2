package ro.sci.ems.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ro.sci.ems.domain.Employee;
import ro.sci.ems.service.EmployeeService;
import ro.sci.ems.service.SecurityService;
import ro.sci.ems.service.ValidationException;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("add")
	public ModelAndView renderAdd() {
		ModelAndView modelAndView = new ModelAndView("employee/add");
		modelAndView.addObject("employee", new Employee());
		return modelAndView;
	}

	@RequestMapping("edit")
	public ModelAndView renderEdit(long id) {
		ModelAndView modelAndView = new ModelAndView("employee/add");
		modelAndView.addObject("employee", employeeService.get(id));
		return modelAndView;
	}

	@RequestMapping("save")
	public ModelAndView save(
			@Valid @ModelAttribute("employee") Employee employee, 
			BindingResult bindingResult) {
		ModelAndView modelAndView = null;
		boolean hasErros = false;
		if (!bindingResult.hasErrors()) {
			try {
				employeeService.save(employee);

				modelAndView = new ModelAndView();
				modelAndView.setView(new RedirectView(""));
			} catch (ValidationException ex) {
				for (String msg : ex.getCauses()) {
					bindingResult.addError(new ObjectError("employee", msg));
				}
				hasErros = true;
			}
		} else {
			hasErros = true;
		}

		if (hasErros) {
			modelAndView = new ModelAndView("employee/add");
			modelAndView.addObject("employee", employee);
			modelAndView.addObject("errors", bindingResult.getAllErrors());
		}

		return modelAndView;
	}

	@RequestMapping("")
	public ModelAndView list() throws Exception {
		ModelAndView modelAndView = new ModelAndView("employee/list");
		modelAndView.addObject("employees", employeeService.listAll());
		modelAndView.addObject("currentUser", 
				securityService.getCurrentUser());
		
		return modelAndView;
	}
}
