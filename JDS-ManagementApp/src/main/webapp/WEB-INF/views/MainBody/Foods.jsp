<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- main content start-->
<div id="page-wrapper" ng-controller="Foods as fctrl">
	<div class="main-page">
		<div class="tables">
			<h3 class="title1">Manage Foods
				<button ng-hide="fctrl.saveFood" class="btn btn-info" ng-click="fctrl.saveFood = true">Add Food</button>
			</h3>

			<div class="alert alert-success" ng-cloak ng-show="fctrl.successMessage">
				<strong>Success!</strong> {{fctrl.successMessage}}
			</div>

			<div class="alert alert-danger" ng-cloak ng-show="fctrl.errorMessage">
				<strong>Error!</strong> {{fctrl.errorMessage}}.
			</div>


			<div class="panel panel-primary" ng-cloak ng-show="fctrl.saveFood">
				<div class="panel-heading">
					<strong ng-cloak>{{fctrl.food.foodId ? 'Update' : 'Add'}} Food</strong>
					<button ng-click="fctrl.saveFood = false" class="toggle-hide btn btn-default">
						<i class="glyphicon glyphicon-minus"></i>
					</button>
				</div>
				<div class="panel-body">
					<form ng-submit="fctrl.submit()" name="foodForm">
						<div class="row">
							<div class="col-sm-4">
								<input type="hidden" ng-model="fctrl.food.foodId"/>
								<div class="form-group">
									<label>Food Name</label>
									<input required mingLength="2" type="text" ng-model="fctrl.food.foodName" class="form-control" />
								</div>
								<div class="form-group">
									<label>Category</label>
									<select required class="form-control" 
									ng-model="fctrl.food.category.categoryId"
									ng-options="cat.categoryId as cat.categoryName for cat in fctrl.listCategories">
									
									</select>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label>Image</label>
									<img src="{{fctrl.food.image}}" id="img-preview" width="200" />
									<input id="input-image" type="hidden" ng-model="fctrl.food.image"/>
									<input class="form-control btn btn-default" id="file-upload" type="file"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<button 
								ng-disable="saveFoodForm.$pristine"
								class="btn btn-info" ng-cloak>{{fctrl.food.foodId ? 'Update' : 'Add'}}</button>
								<button type="button" ng-click="fctrl.reset()" class="btn btn-warning">Reset</button>
							</div>
						</div>
					</form>
				</div>
			</div>

			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="row">
						<div class="col-sm-7">
							<div class="form-inline">
								<div class="form-group">
									<button class="btn btn-default btn-sm" ng-disabled="fctrl.page === 1" ng-click="fctrl.decreasePage();">
										<i class="glyphicon glyphicon-menu-left"></i>
									</button>
								</div>

								<div class="form-group">
									<select ng-change="fctrl.changePage()" ng-options="n for n in fctrl.range(1, fctrl.listFoods.totalPages, 1)" ng-model="fctrl.page"
									 class="form-control input-sm">
									</select>
								</div>


								<button class="btn btn-default btn-sm" ng-cloak>{{fctrl.page}}/{{fctrl.listFoods.totalPages}}</button>


								<button class="btn btn-default btn-sm" ng-disabled="fctrl.page === fctrl.listFoods.totalPages" ng-click="fctrl.increasePage(fctrl.listFoods)">
									<i class="glyphicon glyphicon-menu-right"></i>
								</button>

								<div class="form-group">
									<label>Records per page : </label>
									<select ng-change="fctrl.changeRecordsPerPage()" ng-options="n for n in fctrl.range(1, 20, 1)" ng-model="fctrl.size" class="form-control input-sm">
									</select>
								</div>

							</div>
						</div>
						<div class="col-sm-5">
							<form name="searchFoodForm" class="form-inline">
								<div class="form-group">
									<select ng-model="fctrl.searchBy" required="true" class="form-control input-sm">
										<option value="foodId">Food Id</option>
										<option value="foodName">Food Name</option>
									</select>
								</div>
								<div class="input-group">
									<input type="text" placeholder="Search here..." required class="form-control input-sm" ng-model="fctrl.searchText" />
									<div class="input-group-btn">
										<button class="btn btn-default btn-sm" ng-click="fctrl.search()" ng-disabled="searchFoodForm.$invalid || searchFoodForm.$pristine"
										 type="submit">
											<i class="glyphicon glyphicon-search"></i>
										</button>
									</div>
								</div>
								<div class="form-group">
									<button class="btn btn-default btn-sm" ng-click="fctrl.reload()" type="button">
										<i class="glyphicon glyphicon-refresh"></i>
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<table class="table table-responsive">
						<thead>
							<tr>
								<th>#</th>
								<th>Id</th>
								<th>Name</th>
								<th>Category</th>
								<th>Image</th>								
								<th>Flags</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-cloak ng-repeat="f in fctrl.listFoods.content">
								<th scope="row">{{fctrl.indexOrder($index)}}</th>
								<td>{{f.foodId}}</td>
								<td>{{f.foodName}}</td>
								<td>{{f.category.categoryName}}</td>
								<td><img alt="" width="100" src="{{f.image}}"></td>
								<td>{{f.flags}}</td>
								<td>
									<a ng-click="fctrl.edit(f.foodId)" class="btn btn-danger"><i class="glyphicon glyphicon-edit"></i></a>
								</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script>

const CLOUDINARY_URL = 'https://api.cloudinary.com/v1_1/duythao56/image/upload';
const CLOUDINARY_UPLOAD_PRESET = 'vpk2kcau';

var imgPreview = document.getElementById('img-preview');
var fileUpload = document.getElementById('file-upload');
var inputImage = document.getElementById('input-image');
fileUpload.addEventListener('change', function(event){
    var file = event.target.files[0];
    console.log
    var formData = new FormData();
    formData.append('file', file);
    formData.append('upload_preset', CLOUDINARY_UPLOAD_PRESET);
    axios({
        url: CLOUDINARY_URL,
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: formData
    })
    .then(function(response){
        console.log(response.data.secure_url);
        imgPreview.src = response.data.secure_url;
        inputImage.value = response.data.secure_url;
    })
    .catch(function(error){
        console.log(error);
    });
});
</script>