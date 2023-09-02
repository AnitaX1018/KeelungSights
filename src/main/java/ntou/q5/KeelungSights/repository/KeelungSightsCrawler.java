package ntou.q5.KeelungSights.repository;

import ntou.q5.KeelungSights.entity.Sight;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class KeelungSightsCrawler {

    @Autowired
    private SightRepository sightRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveItemsToMongoDB() {
        // 先清空資料庫
        mongoTemplate.dropCollection(Sight.class);
        ArrayList<Sight> sights = getItems();

        // 將Sight物件存入MongoDB Atlas
        sightRepository.saveAll(sights);
    }
    private ArrayList<Sight> sights =new ArrayList<>();
    public KeelungSightsCrawler(){
        try {
            Document doc = Jsoup.connect("https://www.travelking.com.tw/tourguide/taiwan/keelungcity/").timeout(30000).get();
            //System.out.println(doc);
            //挑出district的所有sight
            Elements districts = doc.select("div.box h4");
            //System.out.println(districts);
            for(Element district:districts) {
                //System.out.println(district.text());
                Element vec = doc.selectFirst("div.box h4:contains(" + district.text() + ") + ul");
                //System.out.println(vec);
                if (vec != null) {
                    Elements liElements = vec.select("li");
                    //each sight
                    for (int i = 0; i < liElements.size(); i++) {
                        Element li = liElements.get(i);
                        Element aTag = li.selectFirst("a");
                        String title = aTag.attr("title");
                        //save name in sight
                        Sight sight = new Sight();
                        sight.setSightName(title);
                        //System.out.println(title);

                        //each sight's detail
                        String href = aTag.attr("href");
                        try {
                            Document eachDoc = Jsoup.connect("https://www.travelking.com.tw" + href).get();
                            //zone
                            sight.setZone(district.text());

                            //Category
                            Element categoryElement = eachDoc.selectFirst("span.point_type strong");
                            if (categoryElement != null) {
                                String category = categoryElement.text();
                                sight.setCategory(category);
                                //System.out.println(category);
                            } else {
                                sight.setCategory("");
                            }

                            //PhotoURL:
                            Element photoElement = eachDoc.selectFirst("div.g_main img");
                            if (photoElement != null) {
                                String dataSrc = photoElement.attr("data-src");
                                sight.setPhotoURL(dataSrc);
                                //System.out.println(dataSrc);
                            } else {
                                sight.setPhotoURL("https://imgs.qiubiaoqing.com/qiubiaoqing/imgs/606dad0a5db0dpw4.gif");
                            }

                            //description
                            Element descriptionElement = eachDoc.selectFirst("meta[itemprop=description]");
                            if (descriptionElement != null) {
                                String content = descriptionElement.attr("content");
                                sight.setDescription(content);
                                //System.out.println(content);
                            } else {
                                sight.setDescription("");
                            }

                            //Address
                            Element addressElement = eachDoc.selectFirst("div.address span");
                            if (addressElement != null) {
                                String address = addressElement.text();
                                sight.setAddress(address);
                                //System.out.println(address);
                            } else {
                                sight.setAddress("");
                            }

                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        //sight放進陣列
                        sights.add(sight);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //designated zone
    public ArrayList<Sight> getItems(String zone){
        //過濾sights
        ArrayList<Sight> filteredSights = new ArrayList<>();
        for (Sight sight : sights) {
            if (sight.getZone().contains(zone)) {
                filteredSights.add(sight);
            }
        }

        return filteredSights;
    }


    //all sights
    public ArrayList<Sight> getItems(){
        return sights;
    }
}
