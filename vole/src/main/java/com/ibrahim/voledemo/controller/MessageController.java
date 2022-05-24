package com.ibrahim.voledemo.controller;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import com.ibrahim.voledemo.entity.User;
import com.ibrahim.voledemo.repo.ElasticRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController
@RequestMapping("/api")
public class MessageController {

   private ElasticRepo elasticRepo;

    @Autowired
    public MessageController(ElasticRepo elasticRepo) {
        this.elasticRepo = elasticRepo;
    }

    @PostConstruct
    public void init(){

        ArrayList list = new ArrayList();
        User user=new User(UUID.randomUUID().toString(),"ali","merhaba",list);
        elasticRepo.save(user);
    }

    @GetMapping("/healthcheck")
    public String healthCheck(){
        return HttpStatus.OK.toString();
    }

    @PostMapping("/messages")
    public User add(@RequestBody User entity){
        entity.setId(UUID.randomUUID().toString());
        entity.setNested(setHashtag(entity));
        elasticRepo.save(entity);
        return entity;
    }

    @GetMapping("/messages/{id}")
    public Optional<User> user(@PathVariable String id){
        Optional<User> users=elasticRepo.findById(id);
        if(!users.isPresent()){
            throw new RuntimeException("User not found id=> "+id);
        }
        return users;
    }


    @GetMapping("/messages/all")
    public Iterable<User> users(){
        Iterable<User> users=elasticRepo.findAll();
        return users;
    }


    @PutMapping("/update")
    public User upd(@RequestBody User user){
        user.setNested(setHashtag(user));
        elasticRepo.save(user);
        return user;
    }




    @DeleteMapping("/messages/{id}")
    public String delete(@PathVariable String id){
        if(id==null){
            throw new RuntimeException("User not found id=> "+id);
        }
        elasticRepo.deleteById(id);

        return "User deleted successfully!";
    }

    @GetMapping("/messages")
    public ResponseEntity<Iterable<User>> pagination(@RequestParam  int page, @RequestParam  int count,@RequestParam String username){
        Pageable pageable= PageRequest.of(page,count);
        Iterable<User> users=elasticRepo.findByQuery(username,pageable);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(){
        elasticRepo.deleteAll();
        return "Deleted all users succesfully!";
    }


    public List<HashMap> setHashtag(User entity){
        HashMap map = new HashMap();
        ArrayList list = new ArrayList();
        String[] str=entity.getMessage().split("\\s+");
        for (int i=0;i< str.length;i++) {
            if(str[i].charAt(0)=='#'){
                map.put("tag",str[i].substring(1));
                map.put("slug",str[i].substring(1).toLowerCase());
                list.add(map);
            }
            map=new HashMap();

        }

        return list;
    }
}
