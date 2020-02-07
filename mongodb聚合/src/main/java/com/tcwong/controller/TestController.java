package com.tcwong.controller;

import com.tcwong.bean.Book;
import com.tcwong.bean.User;
import com.tcwong.bean.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    private static String USER_COLLECTION= "userCollection";

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }

    @GetMapping("/test2")
    public Object test2() {
        Query query = new Query();
        List<Object> collectiondemo = mongoTemplate.findAll(Object.class, "collectiondemo");
        return collectiondemo;
    }

    /**
     * 插入数据
     * @return
     */
    @GetMapping("/test3")
    public Object test3() {
        User user = new User();
        user.setUserName("kangkang");
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setBookId(3);
        book1.setBookName("红楼梦");
        Book book2 = new Book();
        book2.setBookId(4);
        book2.setBookName("西游记");
        books.add(book1);
        books.add(book2);
        user.setBooks(books);
        User userCollection = mongoTemplate.insert(user);
        return userCollection;

    }

    /**
     * $unwind
     * @return
     */
    @GetMapping("/testUnwind")
    public Object testUnwind() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.unwind("books"),
                Aggregation.match(Criteria.where("books.bookId").is(1))
        );
        AggregationResults<UserVO> userCollection = mongoTemplate.aggregate(aggregation, "userCollection", UserVO.class);
        List<UserVO> mappedResults = userCollection.getMappedResults();
        return mappedResults;
    }

    /**
     * $match
     * @return
     */
    @GetMapping("/testMatch")
    public Object testMatch() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("userName").is("kangkang"))
        );
        AggregationResults<User> userCollection = mongoTemplate.aggregate(aggregation, "userCollection", User.class);
        List<User> mappedResults = userCollection.getMappedResults();
        return mappedResults;
    }

    /**
     * $project
     * @return
     */
    @GetMapping("/testProject")
    public Object testProject() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("books","userName")
                        .and("books").as("book")
                        .and("userName").as("name")
        );
        AggregationResults<Map> aggregate = mongoTemplate.aggregate(aggregation, USER_COLLECTION, Map.class);
        List<Map> mappedResults = aggregate.getMappedResults();
        return mappedResults;
    }

    /**
     * 查询 userCollection 集合所有数据
     * @return
     */
    @GetMapping("/testFindAll")
    public Object testFindAll() {
        List<User> userList = mongoTemplate.findAll(User.class, USER_COLLECTION);
        return userList;
    }

    /**
     * $limit
     * @return
     */
    @GetMapping("/testLimit")
    public Object testLimit() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.limit(1)
        );
        AggregationResults<User> aggregate = mongoTemplate.aggregate(aggregation, USER_COLLECTION, User.class);
        List<User> mappedResults = aggregate.getMappedResults();
        return mappedResults;
    }

    @GetMapping("/testSkip")
    public Object testSkip() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.skip((long) 1)
        );
        AggregationResults<User> aggregate = mongoTemplate.aggregate(aggregation, USER_COLLECTION, User.class);
        List<User> mappedResults = aggregate.getMappedResults();
        return mappedResults;
    }

}
