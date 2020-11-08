function onNavItemActivate(el){
    const prev = $('.active')[0];
    disactivate(prev);
    activate(el);
}
function disactivate(el){
    $(el).removeClass('active');
}
function activate(el) {
    $(el).addClass('active');
}