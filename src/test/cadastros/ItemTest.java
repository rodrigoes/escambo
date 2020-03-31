package test.cadastros;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.cadastro.Item;

public class ItemTest {

	@Test
	public void test() {
		Item item = new Item();

		item.setDescricao("Martelo");
		assertEquals("Martelo", item.getDescricao());
	}
}
