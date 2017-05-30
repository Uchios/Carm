jQuery(function($){
	var page = $('#page');
	$('ul li:first a',page).addClass('selected');
	$('> div',page).load(
			$('ul li:first a',page).attr('href'));
			$('ul li a',page).click(function(e){
				if(!$(this).hasClass('selected')){
					$('> ul li a.selected', page).removeClass('selected');
					$(this).addClass('selected');
					$('> div',page).load($(this).attr('href'));
				}
				e.preventDefault();
			});
});