package main;

import entities.Book;
import entities.Category;
import services.IBook;
import services.ICategory;
import services.impl.BookImpl;
import services.impl.CategoryImpl;

public class Main {
    public static void main(String[] args) {
        /*ICategory dao = new CategoryImpl();
        Category cat = new Category();
        cat.setName("Comedie");
        int ok = dao.create(cat);
        if (ok == 1) {
            System.out.println("Category created successfully");
        }
        else {
            System.out.println("Category not created successfully");
        }*/
        ICategory dao = new CategoryImpl();
       /* Category cat = dao.get(4);
        Scanner scan = new Scanner(System.in);
        System.out.println("Veuillez choisir un nom de categorie");
        cat.setName(scan.nextLine()); //saisir chaine de caractere
        cat.setState(false);
        dao.update(cat);
        dao.delete(6);*/

        for (Category category : dao.getAll()) {
            System.out.println(category);
        }

        // ---- Test BookImpl ----

        IBook bookDao = new BookImpl();
        Book book = new Book();
        book.setTitle("Le Petit Prince 2");
        book.setAuthor("Antoine de Saint-Exupéry");
        book.setIsbn("978-2070612758");
        book.setPublication_year(1943);
        book.setCountPage(96);
        book.setCategory(dao.get(2)); // FK : récupère une Category existante
        int ok = bookDao.create(book);
        if (ok == 1) {
            System.out.println("Book created successfully");
        } else {
            System.out.println("Book not created successfully");
        }

        //IBook bookDao = new BookImpl();
        /*Book book = bookDao.get(1);
        Scanner scan = new Scanner(System.in);
        System.out.println("Veuillez saisir un nouveau titre");
        book.setTitle(scan.nextLine());
        bookDao.update(book);
        bookDao.delete(2);*/

        for (Book b : bookDao.getAll()) {
            System.out.println(b);
        }
    }
}
