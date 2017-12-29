<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- main content start-->
<div id="page-wrapper" ng-controller="Foods as fctrl">
	<div class="main-page">
		<div class="tables">
			<h3 class="title1">Manage Foods
				<button ng-hide="fctrl.saveFood" class="btn btn-info" ng-click="fctrl.saveFood = true">Add Food</button>
			</h3>

			<div class="alert alert-success" ng-show="fctrl.successMessage">
				<strong>Success!</strong> {{fctrl.successMessage}}
			</div>

			<div class="alert alert-danger" ng-show="fctrl.errorMessage">
				<strong>Error!</strong> {{fctrl.errorMessage}}.
			</div>


			<div class="panel panel-primary" ng-show="fctrl.saveFood">
				<div class="panel-heading">
					<strong>{{fctrl.food.foodId ? 'Update' : 'Add'}} Food</strong>
					<button ng-click="fctrl.saveFood = false" class="toggle-hide btn btn-default">
						<i class="glyphicon glyphicon-minus"></i>
					</button>
				</div>
				<div class="panel-body">
					<form ng-submit="fctrl.submit()" name="saveFoodForm">
						<div class="row">
							<div class="col-sm-4">
								<input type="hidden" ng-model="fctrl.food.foodId"/>
								<div class="form-group">
									<label>Food Name</label>
									<input type="text" ng-model="fctrl.food.foodName" class="form-control" />
								</div>
								<div class="form-group">
									<label>Address</label>
									<input type="text" ng-model="fctrl.food.address" class="form-control" />
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label>Phone Number</label>
									<input type="text" ng-model="fctrl.food.phoneNumber" class="form-control" />
								</div>
								<div class="form-group">
									<label>Number Of Table</label>
									<input type="number" ng-model="fctrl.food.numberOfTable" class="form-control" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<button class="btn btn-info">{{fctrl.food.foodId ? 'Update' : 'Add'}}</button>
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


								<button class="btn btn-default btn-sm">{{fctrl.page}}/{{fctrl.listFoods.totalPages}}</button>


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
										<option value="foodName">Food Name</option>
										<option value="address">Address</option>
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
							<tr ng-repeat="f in fctrl.listFoods.content">
								<th scope="row">{{fctrl.indexOrder($index)}}</th>
								<td>{{f.foodId}}</td>
								<td>{{f.foodName}}</td>
								<td>{{f.address}}</td>
								<td><img alt="" src="/images/{{f.image}}"></td>
								<td>{{f.flags}}</td>
								<td>
									<a ng-click="fctrl.edit(f.foodId)" class="btn btn-danger"><i class="glyphicon glyphicon-edit"></i></a>
								</td>
							</tr>

						</tbody>
					</table>
				</div>
=======
<!-- main content start-->
<div id="page-wrapper">
	<div class="main-page">
		<div class="tables">
			<h3 class="title1">Manage Foods</h3>

			<div class="table-responsive bs-example widget-shadow">
				<h4>Responsive Table:</h4>
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>Table heading</th>
							<th>Table heading</th>
							<th>Table heading</th>
							<th>Table heading</th>
							<th>Table heading</th>
							<th>Table heading</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">1</th>
							<td>Table cell</td>
							<td>Table cell</td>
							<td>Table cell</td>
							<td>Table cell</td>
							<td>Table cell</td>
							<td>Table cell</td>
							<td><a class="btn btn-primary">Update</a></td>
						</tr>
						
					</tbody>
				</table>
>>>>>>> refs/remotes/origin/1412163
			</div>
		</div>
	</div>
</div>