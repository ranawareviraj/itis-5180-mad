package edu.uncc.assessment06;

public class CartItem {
    String owner_id;
    String url;
    String name;
    String docId;
    String price;

    public CartItem(String owner_id, String url, String name, String docId, String price) {
        this.owner_id = owner_id;
        this.url = url;
        this.name = name;
        this.docId = docId;
        this.price = price;
    }

    public CartItem() {
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "owner_id='" + owner_id + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", docId='" + docId + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
