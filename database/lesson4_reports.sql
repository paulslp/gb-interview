select *
from films;
select *
from sessions;
select *
from seats;
select *
from sessions;



/*
Сделать запросы, считающие и выводящие в понятном виде:
*/
/*
ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени. 
Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
*/

select shedule1.title, shedule1.start_at, shedule1.duration, shedule2.title, shedule2.start_at, shedule2.duration
from (
         select ROW_NUMBER() OVER (ORDER BY s.start_at)       as serial_number,
                f.id,
                s.id,
                f."title",
                s.start_at,
                f.duration,
                s.start_at + f.duration * interval '1 minute' as end_at
         from films f
                  inner join sessions s ON f.id = s.film_id
         order by s.start_at
     ) shedule1
         inner join
     (
         select ROW_NUMBER() OVER (ORDER BY s.start_at)       as serial_number,
                f.id,
                s.id,
                f."title",
                s.start_at,
                f.duration,
                s.start_at + f.duration * interval '1 minute' as end_at
         from films f
                  inner join sessions s ON f.id = s.film_id
         order by s.start_at
     ) shedule2
     on shedule1.serial_number + 1 = shedule2.serial_number and shedule1.end_at > shedule2.start_at

/*
перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. 
Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
*/

select shedule1.title,
       shedule1.start_at,
       shedule1.duration,
       shedule2.start_at,
       shedule2.start_at - shedule1.end_at as break_duration
from (
         select ROW_NUMBER() OVER (ORDER BY s.start_at)       as serial_number,
                f.id,
                s.id,
                f."title",
                s.start_at,
                f.duration,
                s.start_at + f.duration * interval '1 minute' as end_at
         from films f
                  inner join sessions s ON f.id = s.film_id
         order by s.start_at
     ) shedule1
         inner join
     (
         select ROW_NUMBER() OVER (ORDER BY s.start_at)       as serial_number,
                f.id,
                s.id,
                f."title",
                s.start_at,
                f.duration,
                s.start_at + f.duration * interval '1 minute' as end_at
         from films f
                  inner join sessions s ON f.id = s.film_id
         order by s.start_at
     ) shedule2
     on shedule1.serial_number + 1 = shedule2.serial_number and shedule2.start_at - shedule1.end_at > '30 minute'
order by shedule2.start_at - shedule1.end_at desc

/*
список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс 
и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). 
Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
*/
select f.title                    as "Название фильма",
       s.spectators_count         as "Число посетителей",
       s.spectators_average_count as "Среднее число зрителей за сеанс",
       s.session_sum              as "Сумма сборов"
from films f
         left join (
    select s.film_id,
           count(*)            as spectators_count,
           round(avg(s.id), 2) as spectators_average_count,
           sum(s.price)        as session_sum
    from sessions s
             left join tickets t on s.id = t.session_id
    group by s.film_id
) s on f.id = s.film_id
union all
select 'Итого'             as title,
       count(*)            as spectators_count,
       round(avg(s.id), 2) as spectators_average_count,
       sum(s.price)        as session_sum
from sessions s
         left join tickets t on s.id = t.session_id


/*
число посетителей и кассовые сборы, сгруппированные по времени начала фильма: 
с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
*/
select 'с 9 до 15'                                                 as "период",
       count(*)                                                    as "число посетителей",
       case when sum(price) is not null then sum(price) else 0 end as "кассовые сборы"
from tickets t
         inner join sessions s
                    on t.session_id = s.id
where s.start_at::time >= '09:00'
  and s.start_at::time < '15:00'
union all
select 'с 15 до 18'                                                as "период",
       count(*)                                                    as "число посетителей",
       case when sum(price) is not null then sum(price) else 0 end as "кассовые сборы"
from tickets t
         inner join sessions s
                    on t.session_id = s.id
where s.start_at::time >= '15:00'
  and s.start_at::time < '18:00'
union all
select 'с 18 до 21'                                                as "период",
       count(*)                                                    as "число посетителей",
       case when sum(price) is not null then sum(price) else 0 end as "кассовые сборы"
from tickets t
         inner join sessions s
                    on t.session_id = s.id
where s.start_at::time >= '18:00'
  and s.start_at::time < '21:00'
union all
select 'с 21 до 00:00'                                             as "период",
       count(*)                                                    as "число посетителей",
       case when sum(price) is not null then sum(price) else 0 end as "кассовые сборы"
from tickets t
         inner join sessions s
                    on t.session_id = s.id
where s.start_at::time >= '21:00'
  and s.start_at::time < '00:00'

