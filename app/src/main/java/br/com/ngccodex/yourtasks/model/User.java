package br.com.ngccodex.yourtasks.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by tg8g on 23/04/16.
 */

@JsonIgnoreProperties({ "date" })
//@JsonIgnoreProperties(ignoreUnknown=true)
public class User {

    private String node;
    private String code;
    private String name;
    private String tasklist;
    private long total;


    public User() {
    }

    /**
     * Use this constructor to create new Users.
     *
     * @param code
     * @param name
     * @param tasklist
     * @param total
     */
    public User(String code, String name, String tasklist, long total) {
        this.code = code;
        this.name = name;
        this.tasklist = tasklist;
        this.total = total;
    }

    public String getNode() {
        return node;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getTasklist(){
        return tasklist;
    }

    public long getTotal(){
        return total;
    }

}

