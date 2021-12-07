package com.ynov.b3info;

import java.util.Date;

public class Book {
	
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    private String title;
    private String author;
    private Date publicationDate ;

	public String gettitle() {
		return title;
	}

	public void settitle(String title) {
		this.title = title;
	}

	public String getauthor() {
		return author;
	}

	public void setauthor(String author) {
		this.author = author;
	}

	public Date getpublicationDate() {
		return publicationDate;
	}

	public void setpublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

}
