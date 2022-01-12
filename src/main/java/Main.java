import entity.ToDo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;


public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "todos";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        // read the existing entries and write to console
        Query q = em.createQuery("select t from ToDo t");
        List<ToDo> toDoList = q.getResultList();
        for (ToDo todo : toDoList) {
            System.out.println(todo);
        }
        System.out.println("Size: " + toDoList.size());


        // create new todo
        em.getTransaction().begin();
        ToDo todo = new ToDo();
        todo.setSummary("This is a test XYZ");
        todo.setDescription("Created on "+LocalDateTime.now());
        em.persist(todo);
        em.getTransaction().commit();


//        get todo with id=4
        ToDo toDo1 = em.find(ToDo.class, 4L);
        if(toDo1 !=null){
            System.out.println(toDo1);
        } else{
            System.out.println("No such ToDo");
        }


        //update todo with id=4
//        em.getTransaction().begin();
//        toDo1.setDescription("XYZ");
//        em.getTransaction().commit();
//        System.out.println(toDo1.toString());

        // remove todo
//        em.getTransaction().begin();
//        em.remove(toDo1);
//        em.getTransaction().commit();

        // read the existing entries and write to console
//        Query q1 = em.createQuery("select t.description from ToDo t");
//        List<String> descriptionList = q1.getResultList();
//        for (String s : descriptionList) {
//            System.out.println(s);
//        }
        em.close();
    }
}