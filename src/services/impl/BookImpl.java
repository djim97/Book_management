package services.impl;
import config.ConnectionDB;
import entities.Category;
import services.IBook;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import entities.Book;
import java.sql.ResultSet;

public class BookImpl implements IBook {
    private ConnectionDB db = new ConnectionDB();
    private ResultSet rs;
    private int ok;

    @Override
    public int create(Book book) {
        String sql = "insert into book (created_at, updated_at, title, author, isbn, publication_year, countpage, category_id) values (?,?,?,?,?,?,?,?)";
        try {
            //connexion et preparation de la requete
            db.initPrepar(sql);

            //Passage des valeurs
            db.getPstm().setObject(1, LocalDateTime.now());
            db.getPstm().setObject(2, LocalDateTime.now());
            db.getPstm().setString(3, book.getTitle());
            db.getPstm().setString(4, book.getAuthor());
            db.getPstm().setString(5, book.getIsbn());
            db.getPstm().setInt(6, book.getPublication_year());
            db.getPstm().setInt(7, book.getCountPage());
            db.getPstm().setInt(8, book.getCategory().getId());
            ok = db.executeMaj();
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();//pour voir la trace d'erreur
        }
        return ok;
    }

    @Override
    public int update(Book book) {
        String sql = "update book set updated_at=?, title=?, author=?, isbn=?, publication_year=?, countpage=?, category_id=? where id=?";
        try {
            //connexion et preparation de la requete
            db.initPrepar(sql);

            //Passage des valeurs
            db.getPstm().setObject(1, LocalDateTime.now());
            db.getPstm().setString(2, book.getTitle());
            db.getPstm().setString(3, book.getAuthor());
            db.getPstm().setString(4, book.getIsbn());
            db.getPstm().setInt(5, book.getPublication_year());
            db.getPstm().setInt(6, book.getCountPage());
            db.getPstm().setInt(7, book.getCategory().getId());
            db.getPstm().setInt(8, book.getId());
            ok = db.executeMaj();
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();//pour voir la trace d'erreur
        }
        return ok;
    }

    @Override
    public int delete(int id) {
        String sql = "delete from book where id=?";
        try {
            //connexion et preparation de la requete
            db.initPrepar(sql);

            //Passage des valeurs

            db.getPstm().setInt(1, id);
            ok = db.executeMaj();
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();//pour voir la trace d'erreur
        }
        return ok;
    }

    @Override
    public List<Book> getAll() {
        String sql = "select * from book order by title asc";
        List<Book> books = new ArrayList<Book>();

        try {
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()) {
                //Premiere methode : passage par setters
                /*Category category = new Category();
                category.setId(rs.getInt("id"));//recupere la valeur
                category.setName(rs.getString("name"));
                category.setState(rs.getBoolean("state"));
                category.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                category.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));*/

                //Deuxième methode : passage par constructeur.
                Category cat = new CategoryImpl().get(rs.getInt("category_id"));
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("publication_year"),
                        rs.getInt("countpage"),
                        cat
                );

                //ajout
                books.add(book);
            }
            //fermeture
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //retouner les categories
        return books;
    }

    @Override
    public Book get(int id) {
        String sql = "select * from book where id=?";
        Book book = null;
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            rs = db.executeSelect();
            if (rs.next()) {

                Category cat = new CategoryImpl().get(rs.getInt("category_id"));
                book = new Book(
                        rs.getInt("id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("publication_year"),
                        rs.getInt("countpage"),
                        cat
                );
            }
            //fermeture
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //retouner les categories
        return book;
    }

    @Override
    public List<Book> getAllByAuthor(String author) {
        String sql = "select * from book where author=?";
        List<Book> books = new ArrayList<Book>();
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, author);
            rs = db.executeSelect();
            while (rs.next()) {
                Category cat = new CategoryImpl().get(rs.getInt("category_id"));
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("publication_year"),
                        rs.getInt("countpage"),
                        cat
                );

                //ajout
                books.add(book);
            }
            //fermeture
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //retouner les categories
        return books;

    }


    @Override
    public List<Book> getAllByCategory(String category) {
        String sql = "select b.* from book b join category c on b.category_id = c.id where c.name=?";
        List<Book> books = new ArrayList<Book>();
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, category);
            rs = db.executeSelect();
            while (rs.next()) {
                Category cat = new CategoryImpl().get(rs.getInt("category_id"));
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("publication_year"),
                        rs.getInt("countpage"),
                        cat
                );

                //ajout
                books.add(book);
            }
            //fermeture
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //retouner les categories
        return books;
    }
    @Override
    public List<Book> getAllByPublicationYear(int year) {
        String sql = "select * from book where publication_year=?";
        List<Book> books = new ArrayList<Book>();
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, year);
            rs = db.executeSelect();
            while (rs.next()) {
                Category cat = new CategoryImpl().get(rs.getInt("category_id"));
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("publication_year"),
                        rs.getInt("countpage"),
                        cat
                );

                //ajout
                books.add(book);
            }
            //fermeture
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //retouner les categories
        return books;
    }
}
