/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

/**
 *
 * @author Medard
 */
public class Author {

    private int author_id;
    private String author_username;
    private String author_password;
    private String author_rootDirectory;

    public Author() {
    }

    public Author(String author_username, String author_password, String author_rootDirectory) {
        this.author_username = author_username;
        this.author_password = author_password;
        this.author_rootDirectory = author_rootDirectory;
    }

    public Author(int author_id, String author_username, String author_password, String author_rootDirectory) {
        this.author_id = author_id;
        this.author_username = author_username;
        this.author_password = author_password;
        this.author_rootDirectory = author_rootDirectory;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_password() {
        return author_password;
    }

    public void setAuthor_password(String author_password) {
        this.author_password = author_password;
    }

    public String getAuthor_username() {
        return author_username;
    }

    public void setAuthor_username(String author_username) {
        this.author_username = author_username;
    }

    public String getAuthor_rootDirectory() {
        return author_rootDirectory;
    }

    public void setAuthor_rootDirectory(String author_rootDirectory) {
        this.author_rootDirectory = author_rootDirectory;
    }
    
}
