/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Author;

/**
 *
 * @author Medard
 */
public interface AuthorInterface {

    public Author creatAuthor(Author author);

    public boolean deleteAuthor(int id);

    public boolean updateAuthor(Author author);

    public Author getAuthorById(int id);

    public Author getAuthorByUsername(String username);

    public boolean logAuthor(String username, String password);
}
