create table clickstream_search (
  event_id bigint,
  uuid  character varying(100),
  time_stamp bigint,
  device_id character varying(100),
  request_id character varying(100),
  swuid character VARYING (100),
  tid CHARACTER VARYING (100),
  sid CHARACTER VARYING (100),
  version_code char(20),
  customer_id bigint,
  app_version CHAR (20),
  user_agent char(30),
  search_string CHARACTER  VARYING (200),
  item_array character varying(10000),
  num_unserviceable_restaurants INT,
  num_open_restaurants INTEGER ,
  num_closed_restaurants INTEGER ,
  closed_restaurants character varying(4096),
  unserviceable_restaurants  character varying(4096),
  open_restaurants character varying(4096),
  lat_long character varying(100),
  geo_hash CHAR (20),
  is_banner_up char(20));



create table clickstream_menu (
  event_id bigint,
  uuid  character varying(100),
  time_stamp bigint,
  device_id character varying(100),
  request_id character varying(100),
  swuid character VARYING (100),
  tid CHARACTER VARYING (100),
  sid CHARACTER VARYING (100),
  version_code char(20),
  customer_id bigint,
  app_version CHAR (20),
  user_agent char(300),
  customer_lat_long character(100),
  customer_geo_hash CHAR (20),
  restaurant_id integer,
  restaurant_name CHARACTER(300),
  city character(40),
  area CHARACTER (100),
  avg_rating FLOAT ,
  delivery_time integer,
  min_delivery_time integer,
  max_delivery_time integer,
  is_swiggy_select boolean,
  is_new boolean,
  is_veg boolean,
  is_favourite boolean,
  opened boolean,
  serviceability character(40),
  rain_mode boolean,
  is_long_distance boolean,
  listing_version CHARACTER (100),
  api_version CHARACTER(20)) distkey(sid) sortkey(time_stamp) ;



  create table dominos_event (
    event_id bigint,
  uuid  character varying(100),
  time_stamp bigint,
  device_id character varying(100),
  swuid CHARACTER VARYING (100),
  tid CHARACTER VARYING (100),
  sid CHARACTER VARYING (100),
  customer_id CHARACTER VARYING (100),
  dominos_api_status CHARACTER (100),
  dominos_api_execution_time INTEGER ,
  internal_mapping_found INTEGER ,
  lat_long CHARACTER (30),
  geo_hash CHARACTER (20),
  main_flow_execution_time INTEGER ,
  parallel_flow_merge_successful INTEGER ,
  partner_enrichment_time INTEGER
  ) distkey(sid) sortkey(time_stamp) ;



































)