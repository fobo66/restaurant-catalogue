package com.example.testapplication.util;

import com.example.testapplication.pojo.Categories;
import com.example.testapplication.pojo.Offers;

import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by fobo66 on 26.09.2016.
 */
public class OffersConverterTest {

    @Test
    public void testOfferSerializer() {
        Serializer serializer = new Persister(new AnnotationStrategy());
        String source = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<!DOCTYPE yml_catalog SYSTEM \"shops.dtd\">\n" +
                "<yml_catalog date=\"2016-09-21 22:30\">\n" +
                "    <shop>\n" +
                "      <categories>\n" +
                "          <category id=\"2\">Сеты</category>\n" +
                "          <category id=\"18\">Роллы</category>\n" +
                "          <category id=\"5\">Суши</category>\n" +
                "          <category id=\"23\">Добавки</category>\n" +
                "          <category id=\"20\">Закуски</category>\n" +
                "          <category id=\"10\">Десерты</category>\n" +
                "          <category id=\"9\">Напитки</category>\n" +
                "          <category id=\"24\">Шашлыки</category>\n" +
                "          <category id=\"25\">Патимейкер</category>\n" +
                "          <category id=\"3\">Лапша</category>\n" +
                "          <category id=\"6\">Супы</category>\n" +
                "          <category id=\"7\">Салаты</category>\n" +
                "          <category id=\"8\">Теплое</category>\n" +
                "          <category id=\"1\">Пицца</category>\n" +
                "      </categories>\n" +
                "      <offers>\n" +
                "        <offer id=\"10054\">\n" +
                "          <url>http://ufa.farfor.ru/pizza/ciplenok-barbeku-40spec/</url>\n" +
                "          <name>Цыпленок барбекю 40см</name>\n" +
                "          <price>490.00</price>\n" +
                "          <description>Ветчина, курица жареная, шампиньоны свежие, перец болгарский, лук зеленый, соус барбекю. К пицце подается фирменный томатный соус.</description>\n" +
                "          <picture>http://ufa.farfor.ru/media/menu/products/%D0%A6%D1%8B%D0%BF%D0%BB%D0%B5%D0%BD%D0%BE%D0%BA_%D0%B1%D0%B0%D1%80%D0%B1%D0%B5%D0%BA%D1%8E_09_5.png</picture>\n" +
                "          <categoryId>1</categoryId>\n" +
                "          <param name=\"Диаметр\">40см</param>\n" +
                "          <param name=\"Вес\">1090.00 гр</param>\n" +
                "        </offer>\n" +
                "        <offer id=\"10065\">\n" +
                "          <url>http://ufa.farfor.ru/pizza/ciplenok-barbeku-30-spec/</url>\n" +
                "          <name>Цыпленок барбекю 30см</name>\n" +
                "          <price>249.00</price>\n" +
                "          <description>Ветчина, курица жареная, шампиньоны свежие, перец болгарский, лук зеленый, соус барбекю. К пицце подается фирменный томатный соус.</description>\n" +
                "          <picture>http://ufa.farfor.ru/media/menu/products/%D0%A6%D1%8B%D0%BF%D0%BB%D0%B5%D0%BD%D0%BE%D0%BA_%D0%B1%D0%B0%D1%80%D0%B1%D0%B5%D0%BA%D1%8E_09_4.png</picture>\n" +
                "          <categoryId>1</categoryId>\n" +
                "          <param name=\"Диаметр\">30см</param>\n" +
                "          <param name=\"Вес\">645.00 гр</param>\n" +
                "        </offer>\n" +
                "        <offer id=\"4493\">\n" +
                "          <url>http://ufa.farfor.ru/japan/rolly/zapechennaya-filadelfiya-12-portsii/</url>\n" +
                "          <name>Запеченная Филадельфия 1/2 порции</name>\n" +
                "          <price>189.00</price>\n" +
                "          <description>Сыр Филадельфия, лосось, сырный соус. Запекается в гриль-саламандре.</description>\n" +
                "          <picture>http://ufa.farfor.ru/media/menu/products/225_%D0%A0%D0%BE%D0%BB%D0%BB%D1%8B_%D0%97%D0%B0%D0%BF%D0%B5%D1%87%D0%B5%D0%BD%D0%BD%D0%B0%D1%8F_%D0%A4%D0%B8%D0%BB%D0%B0%D0%B4%D0%B5%D0%BB%D1%8C%D1%84%D0%B8%D1%8F.jpg</picture>\n" +
                "          <categoryId>18</categoryId>\n" +
                "          <param name=\"Диаметр\">1/2 порции</param>\n" +
                "          <param name=\"Вес\">167.00 гр</param>\n" +
                "        </offer>\n" +
                "        <offer id=\"4525\">\n" +
                "          <url>http://ufa.farfor.ru/japan/rolly/filadelfiya-pod-ugryom-12-portsii/</url>\n" +
                "          <name>Филадельфия под угрём 1/2 порции</name>\n" +
                "          <price>169.00</price>\n" +
                "          <description>Новое исполнение знаменитого ролла Филадельфия. Мы заменили лосось обжаренным копченым угрём, добавили ломтики авокадо и традиционный сливочный сыр. Изысканный вкус ролла без сырой рыбы.</description>\n" +
                "          <picture>http://ufa.farfor.ru/media/menu/products/268_%D0%A0%D0%BE%D0%BB%D0%BB%D1%8B_%D0%A4%D0%B8%D0%BB%D0%B0%D0%B4%D0%B5%D0%BB%D1%8C%D1%84%D0%B8%D1%8F_%D0%BF%D0%BE%D0%B4_%D1%83%D0%B3%D1%80%D0%B5%D0%BC.jpg</picture>\n" +
                "          <categoryId>18</categoryId>\n" +
                "          <param name=\"Диаметр\">1/2 порции</param>\n" +
                "          <param name=\"Вес\">115.00 гр</param>\n" +
                "        </offer>\n" +
                "        <offer id=\"4524\">\n" +
                "          <url>http://ufa.farfor.ru/japan/rolly/filadelfiya-delyuks-12-portsii/</url>\n" +
                "          <name>Филадельфия Делюкс 1/2 порции</name>\n" +
                "          <price>189.00</price>\n" +
                "          <description>Лосось охлажденный, омлет Тамаго. Шапка: сливочный сыр.</description>\n" +
                "          <picture>http://ufa.farfor.ru/media/menu/products/%D1%84%D0%B8%D0%BB%D0%B0-%D0%B4%D0%B5%D0%BB%D1%8E%D0%BA%D1%81.jpg</picture>\n" +
                "          <categoryId>18</categoryId>\n" +
                "          <param name=\"Диаметр\">1/2 порции</param>\n" +
                "          <param name=\"Вес\">150.00 гр</param>\n" +
                "        </offer>\n" +
                "      </offers>\n" +
                "    </shop>\n" +
                "</yml_catalog>";

        Offers offers = null;
        try {
            offers = serializer.read(Offers.class, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert offers != null;
        assertNotNull(offers.getOffers());
        assertEquals(5, offers.getOffers().size());
    }

}