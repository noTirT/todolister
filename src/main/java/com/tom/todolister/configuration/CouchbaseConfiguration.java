package com.tom.todolister.configuration;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.manager.bucket.BucketSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.CouchbaseClientFactory;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import javax.annotation.PostConstruct;
import java.util.Set;

@Configuration
@EnableCouchbaseRepositories(basePackages = {"com.tom.todolister"})
@Slf4j
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Value("${couchbase.connection.string}")
    private String couchbaseConnectionString;

    @Value("${couchbase.connection.username}")
    private String username;

    @Value("${couchbase.connection.password}")
    private String password;

    @Value("${couchbase.bucket.name}")
    private String bucketName;

    @PostConstruct
    public void init(){
        Cluster cluster = Cluster.connect(couchbaseConnectionString, username, password);
        Set<String> bucketNames = cluster.buckets().getAllBuckets().keySet();
        if(!bucketNames.contains(bucketName)){
            log.info("Creating new bucket: {}", bucketName);
            BucketSettings bucketSettings = BucketSettings.create(bucketName);
            cluster.buckets().createBucket(bucketSettings);
            cluster.queryIndexes().createPrimaryIndex(bucketName);
        }
    }

    @Override
    public CouchbaseClientFactory couchbaseClientFactory(Cluster couchbaseCluster) {
        return super.couchbaseClientFactory(couchbaseCluster);
    }

    @Override
    public String getConnectionString() {
        return couchbaseConnectionString;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return bucketName;
    }

    @Override
    public String typeKey() {
        return "dataType";
    }
}
