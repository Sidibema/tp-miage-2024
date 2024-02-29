package com.acme.todolist;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import com.acme.todolist.domain.TodoItem;

class TodoItemTest {

    @Test
    void testIsLateFalse() {

        Instant onTime = Instant.now().minusSeconds(3600); // 1 heure avant
        TodoItem item = new TodoItem("1", onTime, "Complete the report");

        assertFalse(item.isLate(), "isLate devrait retourner false pour un item à l'heure.");
    }

    @Test
    void testIsLateTrue() {

        Instant late = Instant.now().minusSeconds(3600 * 25); // 25 heures avant, donc en retard
        TodoItem item = new TodoItem("2", late, "Pay the bills");

        assertTrue(item.isLate(), "isLate devrait retourner true pour un item en retard.");
    }

    @Test
    void testFinalContentReflectsIsLate() {

        Instant late = Instant.now().minusSeconds(3600 * 25); // 25 heures avant
        String content = "Pay the bills";
        TodoItem itemLate = new TodoItem("2", late, content);

        Instant onTime = Instant.now().minusSeconds(3600); // 1 heure avant
        TodoItem itemOnTime = new TodoItem("1", onTime, "Complete the report");

        assertEquals("[LATE!] " + content, itemLate.finalContent(), "Le contenu devrait être préfixé par '[LATE!]' pour un item en retard.");

        assertEquals("Complete the report", itemOnTime.finalContent(), "Le contenu devrait rester inchangé pour un item à l'heure.");
    }
}

