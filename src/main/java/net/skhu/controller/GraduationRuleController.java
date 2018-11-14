package net.skhu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.dto.Department;
import net.skhu.dto.DepartmentCulture;
import net.skhu.dto.DepartmentMajorRule;
import net.skhu.dto.Major;
import net.skhu.dto.RequiredCultureCount;
import net.skhu.dto.RequiredCultureSubject;
import net.skhu.dto.Total;
import net.skhu.dto.Year;
import net.skhu.mapper.DepartmentCultureMapper;
import net.skhu.mapper.DepartmentMajorRuleMapper;
import net.skhu.mapper.DepartmentMapper;
import net.skhu.mapper.MajorMapper;
import net.skhu.mapper.RequiredCultureCountMapper;
import net.skhu.mapper.RequiredCultureSubjectMapper;
import net.skhu.mapper.TotalMapper;
import net.skhu.mapper.YearMapper;

@Controller
public class GraduationRuleController {
	@Autowired DepartmentMapper departmentMapper;
	@Autowired MajorMapper majorMapper;
	@Autowired DepartmentMajorRuleMapper departmentMajorRuleMapper;
	@Autowired DepartmentCultureMapper departmentCultureMapper;
	@Autowired RequiredCultureCountMapper requiredCultureCountMapper;
	@Autowired RequiredCultureSubjectMapper requiredCultureSubjectMapper;
	@Autowired TotalMapper totalMapper;
	@Autowired YearMapper yearMapper;
	
	@RequestMapping("guest/graduationRule")
	public String viewGuest(Model model) {
		List<Department> departments = departmentMapper.findRealDept();
		List<Year> years = yearMapper.years();
		RequiredCultureCount requiredCultureCount = requiredCultureCountMapper.find();
		Total total = totalMapper.find();
		int totalGrade = total.getGrade();
		model.addAttribute("departments", departments);
		model.addAttribute("chapelCount", requiredCultureCount.getChapelCount());
		model.addAttribute("serveCount", requiredCultureCount.getServeCount());
		model.addAttribute("total", totalGrade);
		model.addAttribute("years", years);
		return "guest/graduationRule";
	}
	
	@RequestMapping(value= "guest/select", method = RequestMethod.GET)
	public String viewGuest(Model model, @RequestParam("departmentId") int departmentId, @RequestParam("entranceYear") int entranceYear) {
		Total total = totalMapper.find();
		int totalGrade = total.getGrade();
		DepartmentMajorRule firstRule = null;
		if(departmentId == 31)
			firstRule = departmentMajorRuleMapper.findSecond(departmentId, entranceYear);
		else
			firstRule = departmentMajorRuleMapper.findFirst(departmentId, entranceYear);
		List<DepartmentMajorRule> departmentMajorRules = departmentMajorRuleMapper.findByDepartmentId(departmentId, entranceYear);
		List<DepartmentCulture> departmentCultures = departmentCultureMapper.findByDepartmentId(departmentId, entranceYear);
		List<Major> majors = null;
		if(departmentId == 32) 
			if(entranceYear <= 2013)
				majors =majorMapper.findSoft2013MustMajor(departmentId);
			else
				majors =majorMapper.findSoft2014MustMajor(departmentId);
		else
			majors = majorMapper.findMustMajor(departmentId);
		List<Department> departments = departmentMapper.findRealDept();
		RequiredCultureCount requiredCultureCount = requiredCultureCountMapper.find();
		List<RequiredCultureSubject> requiredCultureSubjects = requiredCultureSubjectMapper.findByYear(entranceYear);
		List<Year> years = yearMapper.years();
		model.addAttribute("departments", departments);
		model.addAttribute("departmentId", departmentId);
		model.addAttribute("entranceYear", entranceYear);
		model.addAttribute("total", totalGrade);
		model.addAttribute("firstRule", firstRule);
		model.addAttribute("departmentMajorRules", departmentMajorRules);
		model.addAttribute("majors", majors);
		model.addAttribute("departmentCultures", departmentCultures);
		model.addAttribute("chapelCount", requiredCultureCount.getChapelCount());
		model.addAttribute("serveCount", requiredCultureCount.getServeCount());
		model.addAttribute("requiredCultureSubjects", requiredCultureSubjects);
		model.addAttribute("years", years);
		return "guest/graduationRule";
	}

	@RequestMapping("user/graduationRule")
	public String viewUser(Model model) {
		List<Department> departments = departmentMapper.findAll();
		model.addAttribute("departments", departments);
		return "user/graduationRule";
	}
	
	@RequestMapping(value="user/graduationRule", method = RequestMethod.GET)
	public String viewUser(Model model, @RequestParam("departmentId") int departmentId, @RequestParam("entranceYear") int entranceYear) {
		Total total = totalMapper.find();
		int totalGrade = total.getGrade();
		DepartmentMajorRule firstRule = departmentMajorRuleMapper.findFirst(departmentId, entranceYear);
		List<DepartmentMajorRule> departmentMajorRules = departmentMajorRuleMapper.findByDepartmentId(departmentId, entranceYear);
		model.addAttribute("departmentId", departmentId);
		model.addAttribute("entranceYear", entranceYear);
		model.addAttribute("total", totalGrade);
		model.addAttribute("firstRule", firstRule);
		model.addAttribute("departmentMajorRules", departmentMajorRules);
		return "user/graduationRule";
	}
	
	//학과별 졸업요건 수정
	@RequestMapping(value="graduation/deptRuleEdit", method = RequestMethod.GET)
	public String choice(Model model, @RequestParam("departmentId") int departmentId, @RequestParam("entranceYear") int entranceYear) {
		DepartmentMajorRule firstRule = departmentMajorRuleMapper.findFirst(departmentId, entranceYear);
		List<DepartmentMajorRule> departmentMajorRules = departmentMajorRuleMapper.findByDepartmentId(departmentId, entranceYear);
		model.addAttribute("departmentId", departmentId);
		model.addAttribute("entranceYear", entranceYear);
		model.addAttribute("firstRule", firstRule);
		model.addAttribute("departmentMajorRules", departmentMajorRules);
		
		return "admin/departmentRuleEdit";
	}
	
	@RequestMapping(value="graduation/deptRuleEdit", method = RequestMethod.POST)
	public String edit(Model model, @RequestParam("departmentId") int departmentId, @RequestParam("entranceYear") int entranceYear, @RequestParam("mustMajor") int[] mustMajors, @RequestParam("choiceMajor") int[] choiceMajors, @RequestParam("mustPlusChoice") int[] mustPlusChoices) {
		/*departmentMajorRuleMapper.updateList(departmentMajorRules);
		return "admin/departmentRuleEdit";*/
		List<DepartmentMajorRule> rules = new ArrayList<DepartmentMajorRule>();
		DepartmentMajorRule rule = new DepartmentMajorRule();
		System.out.println(mustMajors.length);
		for (int i = 0; i < mustMajors.length ; i++) {
			rule.setDepartmentId(departmentId);
			rule.setEntranceYear(i+1 ==1 || i == 2 ? entranceYear : 0);
			rule.setProcessId(i+1);
			rule.setMustMajor(mustMajors[i]);
			rule.setChoiceMajor(choiceMajors[i]);
			rule.setMustPlusChoice(mustPlusChoices[i]);
			rules.add(rule);
		}
		departmentMajorRuleMapper.updateList(rules);
		return "admin/departmentRuleEdit";
	}
	
	//교양 필수 과목 수정
	@RequestMapping("graduation/culturalSubject")
	public String culturalSubject(Model model) {
		RequiredCultureCount requiredCultureCount = requiredCultureCountMapper.find();
		List<RequiredCultureSubject> requiredCultureSubjects = requiredCultureSubjectMapper.findByYear(2016);
		List<RequiredCultureSubject> requiredCultureSubjects2018 = requiredCultureSubjectMapper.findByYear(2018);
		RequiredCultureSubject cultureMust = new RequiredCultureSubject();
		model.addAttribute("requiredCultureCount", requiredCultureCount);
		model.addAttribute("requiredCultureSubjects", requiredCultureSubjects);
		model.addAttribute("requiredCultureSubjects2018", requiredCultureSubjects2018);
		model.addAttribute("cultureMust", cultureMust);
		return "admin/culturalSubject";
	}
	
	@RequestMapping(value="graduation/culturalSubject", method=RequestMethod.POST)
	public String editCultural(Model model, RequiredCultureCount requiredCultureCount) {
		List<RequiredCultureSubject> requiredCultureSubjects = requiredCultureSubjectMapper.findByYear(2016);
		List<RequiredCultureSubject> requiredCultureSubjects2018 = requiredCultureSubjectMapper.findByYear(2018);
		RequiredCultureSubject cultureMust = new RequiredCultureSubject();
		model.addAttribute("requiredCultureSubjects", requiredCultureSubjects);
		model.addAttribute("requiredCultureSubjects2018", requiredCultureSubjects2018);
		model.addAttribute("cultureMust", cultureMust);
		requiredCultureCountMapper.update(requiredCultureCount);
		return "admin/culturalSubject";
	}
	
	@RequestMapping(value="graduation/addSubject1617", method=RequestMethod.POST)
	public String addSubject1617(Model model, RequiredCultureSubject cultureMust){
		requiredCultureSubjectMapper.insert2016(cultureMust);
		requiredCultureSubjectMapper.insert2017(cultureMust);
		return "redirect:culturalSubject";
	}
	
	@RequestMapping(value="graduation/addSubject18", method=RequestMethod.POST)
	public String addSubject18(Model model, RequiredCultureSubject cultureMust){
		requiredCultureSubjectMapper.insert2018(cultureMust);
		return "redirect:culturalSubject";
	}
	
	@RequestMapping(value="graduation/deleteCulture", method=RequestMethod.GET)
	public String deleteCulture(Model model, @RequestParam("subjectId") String subjectId, @RequestParam("entranceYear") int entranceYear) {
		requiredCultureSubjectMapper.delete(subjectId, entranceYear);
		return "redirect:culturalSubject";
	}

}
