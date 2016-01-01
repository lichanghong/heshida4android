package heshida.haihong.com.heshida.Main;

import android.graphics.pdf.PdfDocument;

import java.util.List;

/**
 * Created by lichanghong on 1/1/16.
 */
public class MainModel {
    private String img1;
    private String img2;
    private String img3;
    private List<PageNew> pageNews;

    public MainModel(String img1, String img2, String img3, List<PageNew> pageNews) {
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.pageNews = pageNews;
    }

    public MainModel() {
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }


    public List<PageNew> getPageNews() {
        return pageNews;
    }

    public void setPageNews(List<PageNew> pageNews) {
        this.pageNews = pageNews;
    }

    @Override
    public String toString() {
        return "MainModel{" +
                "img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                ", img3='" + img3 + '\'' +
                ", pageNews=" + pageNews +
                '}';
    }
}

  class PageNew{
      private String newid;
      private String title;

      public PageNew() {
      }

      public PageNew(String newid, String title) {
          this.newid = newid;
          this.title = title;
      }

      public String getNewid() {
          return newid;
      }

      public void setNewid(String newid) {
          this.newid = newid;
      }

      public String getTitle() {
          return title;
      }

      public void setTitle(String title) {
          this.title = title;
      }

      @Override
      public String toString() {
          return "PageNew{" +
                  "newid='" + newid + '\'' +
                  ", title='" + title + '\'' +
                  '}';
      }
  }