/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementors;

import Entities.Author;
import Interfaces.AuthorInterface;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author Medard
 */
public class AuthorInterfaceImpl implements AuthorInterface {

    File UsersXmlFile = new File("C:\\Users\\Medard\\Documents\\NetBeansProjects\\FinalProjectWS\\src\\java\\Entities\\AllUsers.xml");

    public Author creatAuthor(Author author) {
        try {
            DocumentBuilderFactory DBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder DBuilder = DBFactory.newDocumentBuilder();
            Document document = DBuilder.parse(UsersXmlFile);
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            int nextId = Integer.parseInt(root.getAttribute("nextId"));

            //Creating a new user
            Element user = document.createElement("user");
            user.setAttribute("id", Integer.toString(nextId));
            user.setIdAttribute("id", true);
            root.appendChild(user);

            //Appending new user attibutes
            Element username = document.createElement("username");
            Element password = document.createElement("password");
            Element rootDirectory = document.createElement("rootDirectory");
            username.appendChild(document.createTextNode(author.getAuthor_username()));
            password.appendChild(document.createTextNode(author.getAuthor_password()));
            rootDirectory.appendChild(document.createTextNode(author.getAuthor_rootDirectory()));
            user.appendChild(username);
            user.appendChild(password);
            user.appendChild(rootDirectory);

            //Propagating apdates
            root.setAttribute("nextId", Integer.toString(nextId + 1));
            flush(document, UsersXmlFile);

            //Creating the user's directory
            File userDir = new File(author.getAuthor_rootDirectory());
            userDir.mkdirs();

            //Returning
            return new Author(nextId, author.getAuthor_username(), author.getAuthor_password(), author.getAuthor_rootDirectory());

        } catch (SAXException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean deleteAuthor(int id) {
        boolean status = false;
        try {
            DocumentBuilderFactory DBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder DBuilder = DBFactory.newDocumentBuilder();
            Document document = DBuilder.parse(UsersXmlFile);
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();

            NodeList list = root.getElementsByTagName("user");

            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                if (Integer.parseInt(element.getAttribute("id")) == id) {
                    File directory = new File(getAuthorById(id).getAuthor_rootDirectory());
                    if (directory.exists()) {
                        File[] files = directory.listFiles();
                        if (files.length != 0) {
                            for (int j = 0; j < files.length; j++) {
                                files[j].delete();
                            }
                        }
                        directory.delete();
                    }
                    root.removeChild(element);
                    status = true;
                    flush(document, UsersXmlFile);
                    break;
                } else {
                    continue;
                }
            }
        } catch (SAXException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean updateAuthor(Author author) {
        boolean status = false;
        try {
            DocumentBuilderFactory DBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder DBuilder = DBFactory.newDocumentBuilder();
            Document document = DBuilder.parse(UsersXmlFile);
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();

            NodeList list = root.getElementsByTagName("user");

            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                if (Integer.parseInt(element.getAttribute("id")) == author.getAuthor_id()) {
                    NodeList nodesUsername = element.getElementsByTagName("username");
                    NodeList nodesPassword = element.getElementsByTagName("password");
                    NodeList nodesRootDirectory = element.getElementsByTagName("rootDirectory");
                    Text username = (Text) nodesUsername.item(0).getFirstChild();
                    Text password = (Text) nodesPassword.item(0).getFirstChild();
                    Text rootDirectory = (Text) nodesRootDirectory.item(0).getFirstChild();
                    username.setData(author.getAuthor_username());
                    password.setData(author.getAuthor_password());
                    rootDirectory.setData(author.getAuthor_rootDirectory());
                    flush(document, UsersXmlFile);
                    status = true;
                    break;
                } else {
                    continue;
                }
            }

        } catch (SAXException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public Author getAuthorById(int id) {
        Author author = null;
        try {
            DocumentBuilderFactory DBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder DBuilder = DBFactory.newDocumentBuilder();
            Document document = DBuilder.parse(UsersXmlFile);
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();

            NodeList list = root.getElementsByTagName("user");

            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                if (Integer.parseInt(element.getAttribute("id")) == id) {
                    NodeList nodesUsername = element.getElementsByTagName("username");
                    NodeList nodesPassword = element.getElementsByTagName("password");
                    NodeList nodesRootDirectory = element.getElementsByTagName("rootDirectory");
                    Text username = (Text) nodesUsername.item(0).getFirstChild();
                    Text password = (Text) nodesPassword.item(0).getFirstChild();
                    Text rootDirectory = (Text) nodesRootDirectory.item(0).getFirstChild();
                    author = new Author(id, username.getData(), password.getData(), rootDirectory.getData());
                    flush(document, UsersXmlFile);
                    break;
                } else {
                    continue;
                }
            }
        } catch (SAXException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return author;
    }

    public Author getAuthorByUsername(String username) {
        Author author = null;

        try {
            DocumentBuilderFactory DBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder DBuilder = DBFactory.newDocumentBuilder();
            Document document = DBuilder.parse(UsersXmlFile);
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();

            NodeList list = root.getElementsByTagName("user");

            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                Text usrName = (Text) element.getElementsByTagName("username").item(0).getFirstChild();
                if (usrName.getData().equals(username)) {
                    Text pswd = (Text) element.getElementsByTagName("password").item(0).getFirstChild();
                    Text rootD = (Text) element.getElementsByTagName("rootDirectory").item(0).getFirstChild();
                    author = new Author(Integer.parseInt(element.getAttribute("id")), usrName.getData(), pswd.getData(), rootD.getData());
                    flush(document, UsersXmlFile);
                    break;
                } else {
                    continue;
                }
            }
        } catch (SAXException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return author;
    }

    public void flush(Document doc, File xmlFile) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        {
            OutputStream os = null;
            try {
                Transformer transformer = transformerFactory.newTransformer();
                StringWriter stringWriter = new StringWriter();
                StreamResult result = new StreamResult(stringWriter);
                DOMSource source = new DOMSource(doc);
                transformer.transform(source, result);
                String xmlString = stringWriter.toString();
                os = new FileOutputStream(xmlFile);
                byte[] buffer = xmlString.getBytes();
                for (int i = 0; i < buffer.length; i++) {
                    try {
                        os.write(buffer[i]);
                    } catch (IOException ex) {
                        Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                os.close();
                buffer = null;
            } catch (IOException ex) {
                Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(AuthorInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean logAuthor(String username, String password) {
        Author author = getAuthorByUsername(username);
        if (author != null) {
            if (author.getAuthor_password().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
//    public static void main(String[]args){
//
//        if(new AuthorInterfaceImpl().getAuthorByUsername("reber")!=null){
//           System.out.println(new AuthorInterfaceImpl().getAuthorByUsername("reber").getAuthor_rootDirectory());
//        }
//    }
}
