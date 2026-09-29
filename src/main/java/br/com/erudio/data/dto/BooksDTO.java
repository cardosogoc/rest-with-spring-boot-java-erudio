package br.com.erudio.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonPropertyOrder({"id", "author", "launch_date", "price", "title"})
public class BooksDTO extends RepresentationModel<BooksDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @JsonProperty("author")
    @Column(name = "author", nullable = false)
    private String author;

    @JsonProperty("launch_date")
    @Column(name = "launch_date", nullable = false)
    private LocalDateTime launchDate;

    @JsonProperty("price")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @JsonProperty("title")
    @Column(name = "title", nullable = false)
    private String title;

    public BooksDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BooksDTO books)) return false;
        return Objects.equals(getId(), books.getId())
                && Objects.equals(getAuthor(), books.getAuthor())
                && Objects.equals(getLaunchDate(), books.getLaunchDate())
                && Objects.equals(getPrice(), books.getPrice())
                && Objects.equals(getTitle(), books.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getAuthor(),
                getLaunchDate(),
                getPrice(),
                getTitle()
        );
    }
}