package com.echec.demo.service;

import com.echec.demo.entity.User;


public interface LoginService {

    public boolean login(User user);
    public boolean isLogged();

    public boolean isAdmin();

    public int getUserId();

}
