
<!-- main content start-->
<div id="page-wrapper" ng-controller="Departments as dctrl">
	<div class="main-page">
		<div class="tables">
			<h3 class="title1">Manage Departments
				<button ng-hide="dctrl.saveDepartment" class="btn btn-info" ng-click="dctrl.saveDepartment = true">Add Department</button>
			</h3>

			<div class="alert alert-success" ng-cloak ng-show="dctrl.successMessage">
				<strong>Success!</strong> <p ng-bind="dctrl.successMessage"></p>
			</div>

			<div class="alert alert-danger" ng-cloak ng-show="dctrl.errorMessage">
				<strong>Error!</strong> <p ng-bind="dctrl.errorMessage"></p>
			</div>

			<div class="panel panel-primary" ng-cloak ng-show="dctrl.saveDepartment">
				<div class="panel-heading">
					<strong ng-cloak>{{dctrl.department.departmentId ? 'Update' : 'Add'}} Department</strong>
					<button ng-click="dctrl.saveDepartment = false" class="toggle-hide btn btn-default">
						<i class="glyphicon glyphicon-minus"></i>
					</button>
				</div>
				<div class="panel-body">
					<form ng-submit="dctrl.submit()" name="saveDepartmentForm">
						<div class="row">
							<div class="col-sm-4">
								<input type="hidden" ng-model="dctrl.department.departmentId"/>
								<div class="form-group">
									<label>Department Name</label>
									<input type="text" ng-model="dctrl.department.departmentName" class="form-control" />
								</div>
								<div class="form-group">
									<label>Address</label>
									<input type="text" ng-model="dctrl.department.address" class="form-control" />
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label>Phone Number</label>
									<input type="text" ng-model="dctrl.department.phoneNumber" class="form-control" />
								</div>
								<div class="form-group">
									<label>Number Of Table</label>
									<input type="number" ng-model="dctrl.department.numberOfTable" class="form-control" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<button class="btn btn-info" ng-cloak>{{dctrl.department.departmentId ? 'Update' : 'Add'}}</button>
								<button type="button" ng-click="dctrl.reset()" class="btn btn-warning">Reset</button>
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
									<button class="btn btn-default btn-sm" ng-disabled="dctrl.page === 1" ng-click="dctrl.decreasePage();">
										<i class="glyphicon glyphicon-menu-left"></i>
									</button>
								</div>

								<div class="form-group">
									<select ng-change="dctrl.changePage()" ng-options="n for n in dctrl.range(1, dctrl.listDepartments.totalPages, 1)" ng-model="dctrl.page"
									 class="form-control input-sm">
									</select>
								</div>


								<button class="btn btn-default btn-sm" ng-cloak>{{dctrl.page}}/{{dctrl.listDepartments.totalPages}}</button>


								<button class="btn btn-default btn-sm" ng-disabled="dctrl.page === dctrl.listDepartments.totalPages" ng-click="dctrl.increasePage(dctrl.listDepartments)">
									<i class="glyphicon glyphicon-menu-right"></i>
								</button>

								<div class="form-group">
									<label>Records per page : </label>
									<select ng-change="dctrl.changeRecordsPerPage()" ng-options="n for n in dctrl.range(1, 20, 1)" ng-model="dctrl.size" class="form-control input-sm">
									</select>
								</div>

							</div>
						</div>
						<div class="col-sm-5">
							<form name="searchDepartmentForm" class="form-inline">
								<div class="form-group">
									<select ng-model="dctrl.searchBy" required="true" class="form-control input-sm">
										<option value="departmentId">Department Id</option>
										<option value="departmentName">Department Name</option>
										<option value="address">Address</option>
									</select>
								</div>
								<div class="input-group">
									<input type="text" placeholder="Search here..." required class="form-control input-sm" ng-model="dctrl.searchText" />
									<div class="input-group-btn">
										<button class="btn btn-default btn-sm" ng-click="dctrl.search()" ng-disabled="searchDepartmentForm.$invalid || searchDepartmentForm.$pristine"
										 type="submit">
											<i class="glyphicon glyphicon-search"></i>
										</button>
									</div>
								</div>
								<div class="form-group">
									<button class="btn btn-default btn-sm" ng-click="dctrl.reload()" type="button">
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
								<th>Address</th>
								<th>Phone Number</th>
								<th>Number Of Table</th>
								<th>Flags</th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="d in dctrl.listDepartments.content">
								<th scope="row" ng-bind="dctrl.indexOrder($index)"></th>
								<td ng-bind="d.departmentId"></td>
								<td ng-bind="d.departmentName"></td>
								<td ng-bind="d.address"></td>
								<td ng-bind="d.phoneNumber"></td>
								<td ng-bind="d.numberOfTable"></td>
								<td ng-bind="d.flags"></td>
								<td>
									<a ng-click="dctrl.edit(d.departmentId)" class="btn btn-danger"><i class="glyphicon glyphicon-edit"></i></a>
								</td>
								<td>
									<a href="${pageContext.servletContext.contextPath}/Menu?deptId={{d.departmentId}}" class="btn btn-success">Menu</a>
								</td>
								<td>
									<a href="${pageContext.servletContext.contextPath}/Tables?deptId={{d.departmentId}}" class="btn btn-info">Table</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>