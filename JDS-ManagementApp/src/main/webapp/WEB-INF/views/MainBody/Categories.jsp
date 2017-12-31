<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- main content start-->
<div id="page-wrapper" ng-controller="Categories as cctrl">
	<div class="main-page">
		<div class="tables">
			<h3 class="title1">Manage Categories
				<button ng-hide="cctrl.saveCategory" class="btn btn-info" ng-click="cctrl.saveCategory = true">Add Category</button>
			</h3>

			<div class="alert alert-success" ng-cloak ng-show="cctrl.successMessage">
				<strong>Success!</strong> <p ng-bind="cctrl.successMessage"></p>
			</div>

			<div class="alert alert-danger" ng-cloak ng-show="cctrl.errorMessage">
				<strong>Error!</strong> <p ng-bind="cctrl.errorMessage"></p>
			</div>


			<div class="panel panel-primary" ng-cloak ng-show="cctrl.saveCategory">
				<div class="panel-heading">
					<strong ng-cloak>{{cctrl.category.categoryId ? 'Update' : 'Add'}} Category</strong>
					<button ng-click="cctrl.saveCategory = false" class="toggle-hide btn btn-default">
						<i class="glyphicon glyphicon-minus"></i>
					</button>
				</div>
				<div class="panel-body">
					<form ng-submit="cctrl.submit()" name="saveCategoryForm">
						<div class="row">
							<div class="col-sm-4">
								<input type="hidden" ng-model="cctrl.category.categoryId"/>
								<div class="form-group">
									<label>Category Name</label>
									<input type="text" ng-model="cctrl.category.categoryName" class="form-control" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<button class="btn btn-info">{{cctrl.category.categoryId ? 'Update' : 'Add'}}</button>
								<button type="button" ng-click="cctrl.reset()" class="btn btn-warning">Reset</button>
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
									<button class="btn btn-default btn-sm" ng-disabled="cctrl.page === 1" ng-click="cctrl.decreasePage();">
										<i class="glyphicon glyphicon-menu-left"></i>
									</button>
								</div>

								<div class="form-group">
									<select ng-change="cctrl.changePage()" ng-options="n for n in cctrl.range(1, cctrl.listCategories.totalPages, 1)" ng-model="cctrl.page"
									 class="form-control input-sm">
									</select>
								</div>


								<button class="btn btn-default btn-sm" ng-cloak>{{cctrl.page}}/{{cctrl.listCategories.totalPages}}</button>


								<button class="btn btn-default btn-sm" ng-disabled="cctrl.page === cctrl.listCategories.totalPages" ng-click="cctrl.increasePage(cctrl.listCategories)">
									<i class="glyphicon glyphicon-menu-right"></i>
								</button>

								<div class="form-group">
									<label>Records per page : </label>
									<select ng-change="cctrl.changeRecordsPerPage()" ng-options="n for n in cctrl.range(1, 20, 1)" ng-model="cctrl.size" class="form-control input-sm">
									</select>
								</div>

							</div>
						</div>
						<div class="col-sm-5">
							<form name="searchCategoryForm" class="form-inline">
								<div class="form-group">
									<select ng-model="cctrl.searchBy" required="true" class="form-control input-sm">
										<option value="categoryId">Category Id</option>
										<option value="categoryName">Category Name</option>
									</select>
								</div>
								<div class="input-group">
									<input type="text" placeholder="Search here..." required class="form-control input-sm" ng-model="cctrl.searchText" />
									<div class="input-group-btn">
										<button class="btn btn-default btn-sm" ng-click="cctrl.search()" ng-disabled="searchCategoryForm.$invalid || searchCategoryForm.$pristine"
										 type="submit">
											<i class="glyphicon glyphicon-search"></i>
										</button>
									</div>
								</div>
								<div class="form-group">
									<button class="btn btn-default btn-sm" ng-click="cctrl.reload()" type="button">
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
								<th>Flags</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="c in cctrl.listCategories.content">
								<th scope="row" ng-bind="cctrl.indexOrder($index)"></th>
								<td ng-bind="c.categoryId"></td>
								<td ng-bind="c.categoryName"></td>
								<td ng-bind="c.flags"></td>
								<td>
									<a ng-click="cctrl.edit(c.categoryId)" class="btn btn-danger"><i class="glyphicon glyphicon-edit"></i></a>
								</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>