<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- main content start-->
<div id="page-wrapper" ng-controller="Menus as mctrl">
	<div class="main-page">
		<div class="tables">
			<h3 class="title1">Menu For Department ${department.departmentName }
				<button ng-hide="mctrl.saveMenu" class="btn btn-info" ng-click="mctrl.saveMenu = true;mctrl.isUpdate = false ">Add Food</button>
			</h3>

			<div class="alert alert-success" ng-cloak ng-show="mctrl.successMessage">
				<strong>Success!</strong> {{mctrl.successMessage}}
			</div>

			<div class="alert alert-danger" ng-cloak ng-show="mctrl.errorMessage">
				<strong>Error!</strong> {{mctrl.errorMessage}}.
			</div>


			<div class="panel panel-primary" ng-cloak ng-show="mctrl.saveMenu">
				<div class="panel-heading">
					<strong>{{mctrl.isUpdate ? 'Update Food For' : 'Add Food To'}}  Menu</strong>
					<button ng-click="mctrl.saveMenu = false" class="toggle-hide btn btn-default">
						<i class="glyphicon glyphicon-minus"></i>
					</button>
				</div>
				<div class="panel-body">
					<form ng-submit="mctrl.submit()" name="menuForm">
						<div class="row">
							<div class="col-sm-4">
								<input id="deptId" type="hidden" value="${department.departmentId }" ng-model="mctrl.menu.departmentId"/>
								<div class="form-group">
									<label>Food Id</label>
									<input ng-disabled ="mctrl.isUpdate" type="text" ng-model="mctrl.menu.id.foodId" class="form-control" />
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label>Price</label>
									<input type="number" ng-model="mctrl.menu.price" class="form-control" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<button class="btn btn-info" ng-cloak>{{mctrl.isUpdate ? 'Update' : 'Add'}}</button>
								<button type="button" ng-click="mctrl.reset()" class="btn btn-warning">Reset</button>
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
									<button class="btn btn-default btn-sm" ng-disabled="mctrl.page === 1" ng-click="mctrl.decreasePage();">
										<i class="glyphicon glyphicon-menu-left"></i>
									</button>
								</div>

								<div class="form-group">
									<select ng-change="mctrl.changePage()" ng-options="n for n in mctrl.range(1, mctrl.listMenus.totalPages, 1)" ng-model="mctrl.page"
									 class="form-control input-sm">
									</select>
								</div>


								<button class="btn btn-default btn-sm" ng-cloak>{{mctrl.page}}/{{mctrl.listMenus.totalPages}}</button>


								<button class="btn btn-default btn-sm" ng-disabled="mctrl.page === mctrl.listMenus.totalPages" ng-click="mctrl.increasePage(mctrl.listMenus)">
									<i class="glyphicon glyphicon-menu-right"></i>
								</button>

								<div class="form-group">
									<label>Records per page : </label>
									<select ng-change="mctrl.changeRecordsPerPage()" ng-options="n for n in mctrl.range(1, 20, 1)" ng-model="mctrl.size" class="form-control input-sm">
									</select>
								</div>

							</div>
						</div>
						<div class="col-sm-5">
							<form name="searchMenuForm" class="form-inline">
								<div class="form-group">
									<select ng-model="mctrl.searchBy" required="true" class="form-control input-sm">
										<option value="food.foodName">Food Name</option>
										<option value="price">Price</option>
										<option value="address">Address</option>
									</select>
								</div>
								<div class="input-group">
									<input type="text" placeholder="Search here..." required class="form-control input-sm" ng-model="mctrl.searchText" />
									<div class="input-group-btn">
										<button class="btn btn-default btn-sm" ng-click="mctrl.search()" ng-disabled="searchMenuForm.$invalid || searchMenuForm.$pristine"
										 type="submit">
											<i class="glyphicon glyphicon-search"></i>
										</button>
									</div>
								</div>
								<div class="form-group">
									<button class="btn btn-default btn-sm" ng-click="mctrl.reload()" type="button">
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
								<th>Food Id</th>
								<th>Food Name</th>
								<th>Department Name</th>
								<th>Image</th>								
								<th>Price</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-cloak ng-repeat="m in mctrl.listMenus.content">
								<th scope="row">{{mctrl.indexOrder($index)}}</th>
								<td>{{m.id.foodId}}</td>
								<td>{{m.food.foodName}}</td>
								<td>{{m.department.departmentName}}</td>
								<td><img alt="" width="100" src="{{m.food.image}}"></td>
								<td>{{m.price}}</td>
								<td>
									<a ng-click="mctrl.edit(m.id.foodId, m.id.departmentId)" class="btn btn-danger"><i class="glyphicon glyphicon-edit"></i></a>
								</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>