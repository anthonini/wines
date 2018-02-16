var Wines = Wines || {};

Wines.onSidebarToggleRequest = function(event) {
  event.preventDefault();
  $(this).blur();

  $('.js-sidebar, .js-content').toggleClass('is-toggled');
};

Wines.initMenu = function() {
  $('.js-menu > ul > li > a').bind('click', Wines.onMenuGroupClick);
  $('.wines-menu__item .is-active').parents('.wines-menu__item').addClass('is-expanded is-active');
};

$(function() {
  Wines.initMenu();
  
  // Enable Bootstrap tooltip
  $('.js-tooltip').tooltip();
  
  // Bind events
  $('.js-sidebar-toggle').bind('click', Wines.onSidebarToggleRequest);
});