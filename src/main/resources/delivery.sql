

CREATE TABLE create_order_batch(uuid varchar(100),
event_id bigint,
time_stamp bigint,
batch_id bigint,
order_ids varchar(4096),
id varchar(100),
city integer)
sortkey(time_stamp);

CREATE TABLE filter_orders_for_batching(uuid varchar(100),
event_id bigint,
time_stamp bigint,
city integer,
total_bill real,
parent_batch_id bigint,
id varchar(100),
customer_distance real,
restaurant_ids varchar(4096),
child_batch_id bigint,
sla integer)
sortkey(time_stamp);

CREATE TABLE compile_final_batch_list(
	uuid varchar(100),
    event_id      bigint,
    time_stamp   bigint,
	city integer,
	total_bill real,
	parent_batch_id bigint,
	id varchar(100),
	customer_distance real,
	restaurant_ids varchar(4096),
	child_batch_id bigint,
	sla integer
)sortkey(time_stamp);

CREATE TABLE compile_batches_for_assignment(
	uuid varchar(100),
    event_id      bigint,
    time_stamp   bigint,
	unassigned_batches bigint,
	city integer,
	id varchar(100)
)sortkey(time_stamp);

CREATE TABLE compile_de_for_assignment(
	uuid varchar(100),
    event_id      bigint,
    time_stamp   bigint,
	de_id bigint,
	city integer,
	id varchar(100),
	wait_time integer,
	status varchar(40),
	location varchar(100),
	geo_hash varchar(40)
)sortkey(time_stamp);

CREATE TABLE compute_jit_delay(
	uuid varchar(100),
    event_id      bigint,
    time_stamp   bigint,
	calculated_jit real,
	city integer,
	id varchar(100),
	batch_id bigint
)sortkey(time_stamp);

CREATE TABLE filter_edges_first_mile(
	uuid varchar(100),
    event_id      bigint,
    time_stamp   bigint,
	city integer,
	id varchar(100),
	dynamic_first_mile_distance_computed real,
	idle_time real,
	first_mile real,
	de_id bigint,
	batch_id bigint
)sortkey(time_stamp);

CREATE TABLE compute_edge_score(
uuid varchar(100),
event_id bigint,
time_stamp bigint,
low_cross_area_score real,
cycle_de_Score real,
city integer,
low_prepaid_restaurant_score real,
low_reject_score real,
wait_time integer,
low_with_de_score real,
first_mile_score real,
cross_area_score real,
within_shift_score real,
order_delay_score real,
with_de_score real,
low_long_distance_score real,
id varchar(100),
free_de_score real,
high_prepaid_order_score real,
low_next_order_score real,
total_score real,
de_id bigint,
first_mile real,
order_delay_panic_score real,
idle_time_score real,
batch_id bigint,
low_de_batch_score real,
de_wait_time_score real,
zone_list varchar(4096)
) sortkey(time_stamp);

CREATE TABLE assign_de(
uuid varchar(100),
event_id      bigint,
time_stamp   bigint,
score real,
city integer,
id varchar(100),
first_mile real,
de_id bigint,
batch_id bigint
)sortkey(time_stamp);

CREATE TABLE update_de_order_status(
uuid varchar(100),
event_id      bigint,
time_stamp   bigint,
source varchar(40),
order_id bigint,
lat_lng varchar(100),
de_id bigint,
status varchar(40),
geo_hash varchar(40)
)sortkey(time_stamp);

CREATE TABLE de_attendance_data(
uuid varchar(100),
event_id      bigint,
time_stamp   bigint,
start_duty_time bigint,
stop_duty_time bigint,
stop_duty_event varchar(40),
de_id bigint,
start_duty_event varchar(40)
)sortkey(time_stamp);








