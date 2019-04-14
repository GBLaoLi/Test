package com.example.api.controller;

import com.example.api.dao.AuthRepository;
import com.example.api.entity.Authority;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/authManagement")
public class AuthController {

    @Autowired
    AuthRepository authRepository;

    @PostMapping("/define")
    public ModelAndView defineAuth(@RequestParam Integer id,
                                   @RequestParam String code,
                                   @RequestParam String name,
                                   @RequestParam String remark,
                                   Map<String,Object> map){
        Authority authority = authRepository.findByAuthId(id);
        if(authority != null){
            map.put("exist",1);
            return new ModelAndView("",map);
        }
        authority = new Authority(id,code,name,remark);
        authRepository.save(authority);
        map.put("new",authority);
        return new ModelAndView("",map);
    }

    @GetMapping("")
    public ModelAndView findAll(@RequestParam("page") Integer page,
                       @RequestParam("size") Integer size,
                       Map<String,Object> map){
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.ASC,"id");
        Page<Authority> authPage = authRepository.findAll(pageable);
        List<Authority> authorities = authPage.getContent();
        System.out.println(authorities.toString());
        map.put("authList",authorities);
        return new ModelAndView("",map);
    }

    @GetMapping("/{id}")
    public ModelAndView findOne(@PathVariable("id") Integer id,
                        Map<String,Object> map){
        Optional<Authority> op = authRepository.findById(id);
        Authority authority = op.get();
        map.put("authority",authority);
        return new ModelAndView("",map);
    }

    @PutMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") Integer id,
                       @RequestParam("code") String code,
                       @RequestParam("name") String name,
                       @RequestParam("remark") String remark,
                       Map<String,Object> map){
        Optional<Authority> op = authRepository.findById(id);
        Authority authority = op.get();
        authority.setCode(code);
        authority.setName(name);
        authority.setRemark(remark);
        authRepository.saveAndFlush(authority);
        map.put("authority",authority);
        return new ModelAndView("",map);
    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id,
                       Map<String,Object> map){
        authRepository.deleteById(id);
        return new ModelAndView("");
    }

}
