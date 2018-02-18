var Wines = Wines || {};

Wines.DeleteConfirmatioModal = (function() {
	function DeleteConfirmatioModal() {
		this.modal = $('#deleteConfirmationModal');
		this.form = this.modal.find('form');
	}
	
	DeleteConfirmatioModal.prototype.initiate = function() {
		this.modal.on('show.bs.modal', showBsModal.bind(this));		
	}
	
	function showBsModal(event) {
		var button = $(event.relatedTarget);
		var name = button.data('name');
		var url = button.data('url-delete')
		
		this.modal.find('.modal-body span').html('Are you sure you want to delete <strong>'+name+'</sstrong>?');
		
		this.form.attr('action', url);
	}
	
	return DeleteConfirmatioModal;
}());

$(function () {
	var deleteConfirmatioModal = new Wines.DeleteConfirmatioModal();
	deleteConfirmatioModal.initiate();
});