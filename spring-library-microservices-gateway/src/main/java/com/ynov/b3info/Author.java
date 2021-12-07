package com.ynov.b3info;

import java.util.Date;

public class Author {

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    private String name;
    private Date birthday;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getbirthday() {
		return birthday.toString();
	}

	public void setbirthday(Date birthday) {
		this.birthday = birthday;
	}
    
}
