package com.ads.multiplebd.store.models;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends User {
}
