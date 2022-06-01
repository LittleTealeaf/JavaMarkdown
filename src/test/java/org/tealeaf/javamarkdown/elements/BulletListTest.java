package org.tealeaf.javamarkdown.elements;
import org.junit.jupiter.api.Test;
import org.tealeaf.javamarkdown.elements.BulletList;
import test.Tests;
import static org.junit.jupiter.api.Assertions.*;


public class BulletListTest {

    @Test
    void testNameLessBulletList() {

        Object[] obj = {Tests.randomWord(),Tests.randomWord(),Tests.randomWord()};
        BulletList bulletList = new BulletList(obj);
        assertEquals(bulletList.toString(),bulletList.asString());
        assertEquals('-', bulletList.asString().charAt(0));
        assertEquals(obj[2].toString().charAt(obj[2].toString().length()-1),
                bulletList.asString().charAt(bulletList.asString().length()-1));
    }

    @Test
    void testNamedBulletList(){
        Object[] obj = {Tests.randomWord(),Tests.randomWord(),Tests.randomWord()};
        String name = Tests.randomWord();
        BulletList NamedBulletList = new BulletList(name, obj);
        assertEquals(NamedBulletList+"",NamedBulletList.asString());
        assertEquals(obj[2].toString().charAt(obj[2].toString().length()-1),
                NamedBulletList.asString().charAt(NamedBulletList.asString().length()-1));
        assertNotEquals('-', NamedBulletList.asString().charAt(0));
    }

    @Test
    void testNamedEmptyBulletList() {
        String name = Tests.randomWord();
        BulletList NamedEmptyBulletList = new BulletList(name);
        assertEquals(name + "\n",NamedEmptyBulletList.asString());
        assertTrue(!name.isEmpty() && !NamedEmptyBulletList.asString().isEmpty());
    }

    @Test
    void testEmptyBulletList() {
        BulletList EmptyBulletList = new BulletList();
        assertEquals("",EmptyBulletList.asString());
        assertTrue(EmptyBulletList.asString().isBlank() && EmptyBulletList.asString().isBlank());
    }
}
