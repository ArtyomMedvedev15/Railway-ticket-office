 $(function() {
     $("#datepicker_dep_find").datetimepicker({
         minDate: 0,
         dateFormat: 'yy.mm.dd',
         timeFormat: 'HH:mm'
     });
     $("#datepicker_arr_find").datetimepicker({
         minDate: 0,
         dateFormat: 'yy.mm.dd',
         timeFormat: 'HH:mm'
     });
     $("#datepicker_departure").datetimepicker({
         minDate: 0,
         dateFormat: 'yy.mm.dd',
         timeFormat: 'HH:mm'

     });

     $("#datepicker_arrival").datetimepicker({
         minDate: 0,
         dateFormat: 'yy.mm.dd',
         timeFormat: 'HH:mm'
     });

     $("#datepicker_departure_edit").datetimepicker({
         minDate: 0,
         dateFormat: 'yy.mm.dd',
         timeFormat: 'HH:mm'
     });

     $("#datepicker_arrival_edit").datetimepicker({
         minDate: 0,
         dateFormat: 'yy.mm.dd',
         timeFormat: 'HH:mm'
     });
     $("#datepicker_departure_add").datetimepicker({
         minDate: 0,
         dateFormat: 'yy.mm.dd',
         timeFormat: 'HH:mm'
     });
     $("#datepicker_arriva_add").datetimepicker({
         minDate: 0,
         dateFormat: 'yy.mm.dd',
         timeFormat: 'HH:mm'
     });


     $("div.holder").jPages({
         containerID: "itemContainer",
         perPage: 6,
         startPage: 1,
         startRange: 1,
         midRange: 3,
         endRange: 1
     });

 });



 function sort_table(properties) {
     $.each(properties, function(i, val) {

         var orderClass = '';

         $("#" + val).click(function(e) {
             e.preventDefault();
             $('.filter__link.filter__link--active').not(this).removeClass('filter__link--active');
             $(this).toggleClass('filter__link--active');
             $('.filter__link').removeClass('asc desc');

             if (orderClass == 'desc' || orderClass == '') {
                 $(this).addClass('asc');
                 orderClass = 'asc';
             } else {
                 $(this).addClass('desc');
                 orderClass = 'desc';
             }

             var parent = $(this).closest('.header__item');
             var index = $('.header__item').index(parent);
             var $table = $('.table_body');
             var rows = $table.find('.table_row').get();
             var isSelected = $(this).hasClass('filter__link--active');
             var isNumber = $(this).hasClass('filter__link--number');
             var isDate = $(this).hasClass('filter__link--date');

             rows.sort(function(a, b) {

                 var x = $(a).find('.table_data').eq(index).text();
                 var y = $(b).find('.table_data').eq(index).text();

                 if (isDate == true) {

                     if (isSelected) {
                         return new Date(x) - new Date(y);
                     } else {
                         return new Date(y) - new Date(x);
                     }

                 }
                 if (isNumber == true) {

                     if (isSelected) {
                         return x - y;
                     } else {
                         return y - x;
                     }

                 } else {

                     if (isSelected) {
                         if (x < y) return -1;
                         if (x > y) return 1;
                         return 0;
                     } else {
                         if (x > y) return -1;
                         if (x < y) return 1;
                         return 0;
                     }
                 }

             });

             $.each(rows, function(index, row) {
                 $table.append(row);
             });

             return false;
         });

     });
 }

 function init_modal_open_close_btn_buy_ticket() {

     let open_modal = document.querySelectorAll('#open_modal');
     let close_modal = document.querySelectorAll('#close_modal');
     let modal = document.getElementById('modal');
     let body = document.getElementsByTagName('body')[0];

     for (let index = 0; index < open_modal.length; index++) {
         open_modal[index].onclick = function(event) {
             modal.classList.add('modal_vis');
             modal.classList.remove('bounceOutDown');
             body.classList.add('body_block');
             $('#train_from').text(this.dataset.train_from);
             $('#train_to').text(this.dataset.train_to);
             $('#train_number').text(this.dataset.train_number);
             $('#total').text(this.dataset.total_price);
             $('input[id=train_numbers]').val(this.dataset.train_number);
         };
     }
     for (let index = 0; index < close_modal.length; index++) {
         close_modal[index].onclick = function() {
             modal.classList.add('bounceOutDown');
             window.setTimeout(function() {
                 modal.classList.remove('modal_vis');
                 body.classList.remove('body_block');
             }, 500);
         };
     }
 }

 function init_modal_open_close_btn_add_train() {
     let open_modal = document.getElementById('open_modal');
     let close_modal = document.getElementById('close_modal');
     let modal = document.getElementById('modal');
     let body = document.getElementsByTagName('body')[0];

     open_modal.onclick = function() {
         modal.classList.add('modal_vis');
         modal.classList.remove('bounceOutDown');
         body.classList.add('body_block');
     };

     close_modal.onclick = function() {
         modal.classList.add('bounceOutDown');
         window.setTimeout(function() {
             modal.classList.remove('modal_vis');
             body.classList.remove('body_block');
         }, 500);
     };

 }

 function buy() {
     alert("Dear clien thanks for choose us!")
     setTimeout('location.replace("/")', 1000);
 }

 function add_new_train() {
     var arrival_data_time = $('#datepicker_arriva_add').val();
     var departure_data_time = $('#datepicker_departure_add').val();
     var departure_station = $('select[name=Departure_station]').val();
     var arrival_station = $('select[name=arrival_station]').val();

     if (new Date(arrival_data_time).getTime() > new Date(departure_data_time).getTime() && departure_station !== arrival_station) {
         alert("Success add train")
         history.pushState("", document.title, window.location.pathname);
         setTimeout('location.replace("/listTrain")', 100);
         return true;
     } else {
         alert("Error illegal argument dates or stations")
         return false;
     }

 }

 function edit() {
     var arrival_data_time = $('#datepicker_arrival_edit').val();
     var departure_data_time = $('#datepicker_departure_edit').val();
     var departure_station = $('select[name=departure_station_edit]').val();
     var arrival_station = $('select[name=arrival_station_edit]').val();
     var arr_date = Date.parse(arrival_data_time);
     var dep_date = Date.parse(departure_data_time);


     if (arr_date.getTime() > dep_date.getTime() && departure_station !== arrival_station) {
         alert("Success edit train")
         history.pushState("", document.title, window.location.pathname);
         setTimeout('location.replace("/listTrain")', 100);
         return true;
     } else {
         alert("Error illegal argument dates or stations")
         return false;
     }

 }

 function find_train(){
     var arrival_data_time = $('#datepicker_dep_find').val();
     var departure_data_time = $('#ddatepicker_arr_find').val();
     var departure_station = $('select[name=departure_station_find]').val();
     var arrival_station = $('select[name=arrival_station_find]').val();
     var arr_date = Date.parse(arrival_data_time);
     var dep_date = Date.parse(departure_data_time);

     if (arr_date.getTime() > dep_date.getTime() && departure_station !== arrival_station) {
         alert("Success edit train")
         return true;
     } else {
         alert("Error illegal argument dates or stations")
         return false;
     }
 }

 function edit_client() {
     alert("Success edit client")
     setTimeout('location.replace("/listClient")', 100);
 }

 function confirmDelete() {
     if (confirm("Do you confirm the deletion?")) {
         return true;
     } else {
         return false;
     }
 }