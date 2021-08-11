# Company-Stock
This is a Company-Stock portal


ELK (ElasticSearch, Logstash, Kibana) :-> https://www.youtube.com/watch?v=5s9pR9UUtAU&t=1094s

SSL :-> https://www.youtube.com/watch?v=1jCM4jKV68U&t=189s

2 Factor Authentication:-> https://www.youtube.com/watch?v=5MHxCnlXkEY&t=244s

AWS (RDS & EC2): RDS for database in AWS, EC2 for springboot apps in AWS :-> https://www.youtube.com/watch?v=Tbf7F42tcBw&t=694s

Actuator with Prometheus & Grafana :-> https://www.youtube.com/watch?v=gJZhdEJvZmc

Sonarcube :-> https://www.youtube.com/watch?v=hyBznKcoKEg&t=1067s

RabbitMQ: -> https://www.youtube.com/watch?v=o4qCdBR4gUM

SpringAPI Gateway: -> https://www.youtube.com/watch?v=oNagVFsfBqY

======================================================================
user table (posgres):

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) DEFAULT NULL,
  email_id varchar(50) NOT NULL,
  password varchar(1000) DEFAULT NULL,
  mobile varchar(20) DEFAULT NULL,
  country varchar(50) DEFAULT NULL,
  user_type varchar(20) DEFAULT NULL,
  is_2fa_enabled varchar(1) DEFAULT NULL,
  fa_code varchar(45) DEFAULT NULL,
  fa_expire_time varchar(45) DEFAULT NULL,
  fa_default_type varchar(10) DEFAULT NULL
)
**********
company table (posgres):

**********
stock table (mysql):

id int AI PK 
company_code varchar(255) 
end_date date 
start_date date 
stock_price double
