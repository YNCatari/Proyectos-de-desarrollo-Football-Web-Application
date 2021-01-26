(function(window, document, $, undefined){


//$('.time-mask').mask('00:00:00');
$('.time-mask').mask('00:00:00', {placeholder: "__:__:__"}).on('blur', function() {
  	var $this = $(this),
    		v     = $this.val();
    v = v.length == 0 ? '00:00:00' :
         (v.length == 1 ? v+'0:00:00' :
           (v.length == 2 ? v+':00:00' :
             (v.length == 3 ? v+'00:00' :
             (v.length == 4 ? v+'0:00' :
             (v.length == 5 ? v+':00' :
             (v.length == 6 ? v+'00' :
               (v.length == 7 ? v+'0' : v)))))));
  	$this.val( v );
});

$('.time-mask').mask('00:00:00').on('keyup', function() {
    var $this = $(this), v = $this.val();
    console.log(v.length);
    if(v.length == 8)
    {
        $("#btn-d").prop('disabled', false);
    }
    else
    {
        $("#btn-d").prop('disabled', true);
    }
});

$('.dni-mask').mask('99999999', {placeholder: "________"});
$('.phone-mask').mask('99 999 999999', {placeholder: "__ ___ ______"});

$('.sweetalert-season-delete').click(function(e) {
    swal({
        title: '¿Estás seguro?',
        text: "¡No podrás revertir esto!",
        backdrop: `rgba(63, 81, 181, 0.4)`,
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3F51B5',
        cancelButtonColor: '#D01C37',
        confirmButtonText: 'Sí, bórralo!',
        cancelButtonText: 'No, cancelar!',
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger',
        buttonsStyling: true,
        reverseButtons: true
    }).then((result) => {
        if (result.value) {
            var id = $(this).attr("rel");
            $.ajax({
                type: "POST",
            	url: "/admin/season/"+id+"/delete",
            	cache:false,
            	success: function(response) {
                    if(response == 1){
                        $('#season_'+id).fadeOut("slow");
                        swal("¡Eliminada!", "La Temporada ha sido eliminada correctamente.", "success");
                    }else{
                        swal("Oops algo salio mal!", "La Temporada no ha sido eliminada.", "error");
                    }
            	}
            });
        } else if (result.dismiss === swal.DismissReason.cancel) {
            swal('Cancelada','La Temporada está seguro :)','error')
        }
    })
});

//Finally Match
$('.sweetalert-match-finally').click(function(e) {
    e.preventDefault();
    swal({
        title: '¿Estás seguro?',
        text: "¡No podrás revertir esto!",
        backdrop: `rgba(63, 81, 181, 0.4)`,
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3F51B5',
        cancelButtonColor: '#D01C37',
        confirmButtonText: 'Sí, Finalizar Partido!',
        cancelButtonText: 'No, cancelar!',
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger',
        buttonsStyling: true,
        reverseButtons: true
    }).then((result) => {
        if (result.value) {
            var id = $(this).attr("href");
            window.location.href = "/admin/match/"+id+"/state-update";
        } else if (result.dismiss === swal.DismissReason.cancel) {
            swal('Cancelada','El Partido esta abierto :)','error')
        }
    })
});

})(window, document, window.jQuery);