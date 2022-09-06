package talent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import talent.entity.Company;
import talent.service.CompanyService;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/add")
    public String addCompany(Model model) {
        model.addAttribute("addCompany", new Company());
        return "company/addCompany";
    }

    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("addCompany") Company company) {
        companyService.insertCompany(company);
        return "redirect:/companies/getAllCompanies";
    }

    @GetMapping("/getAllCompanies")
    public String getAllCompanies(Model model) {
        model.addAttribute("getAllCompanies", companyService.getAllCompanies());
        return "company/companyPage";
    }

    @GetMapping("/getCompany/{companyId}")
    public String getCompanyById(@PathVariable("companyId") Long companyId, Model model) {
    model.addAttribute("company", companyService.getCompanyById(companyId));
    return "company/companyPage";
    }

    @GetMapping("/updateCompany/{companyId}")
    public String updateCompany(@PathVariable("companyId") Long companyId, Model model) {
        model.addAttribute("company", companyService.getCompanyById(companyId));
        return "company/updateCompany";
    }

    @PostMapping("{companyId}/saveEditedCompany")
    public String saveEditedCompany(@PathVariable("companyId") Long companyId,
                                    @ModelAttribute("company") Company company) {
        companyService.updateCompany(companyId, company);
        return "redirect:/companies/getAllCompanies";
    }

    @PostMapping("/delete/{companyId}")
    public String deleteCompany(@PathVariable("companyId") Long companyId) {
        companyService.deleteCompany(companyId);
        return "redirect:/companies/getAllCompanies";
    }
}
