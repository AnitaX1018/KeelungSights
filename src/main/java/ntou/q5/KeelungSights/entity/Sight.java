package ntou.q5.KeelungSights.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sight")
public class Sight{
    private String sightName;
    private String zone;
    private String category;
    private String photoURL;
    private String description;
    private String address;

    //setter
    public void setSightName(String sightName) {
        this.sightName = sightName;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //getter
    public String getSightName() {
        return sightName;
    }

    public String getZone() {
        return zone;
    }

    public String getCategory() {
        return category;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        String s="";
        s+= "SightName: "+getSightName()+"\n";
        s+="Zone: "+getZone()+"\n";
        s+="Category: "+getCategory()+"\n";
        s+="PhotoURL:"+"\n";
        s+=getPhotoURL()+"\n";
        s+= "Description: "+getDescription()+"\n";
        s+="Address: "+getAddress()+"\n";
        return s;
    }
}