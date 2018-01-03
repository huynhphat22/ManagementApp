
<!-- main content start-->
<div id="page-wrapper" ng-controller="Staffs as sctrl">
	<div class="main-page">
		<div class="tables">
			<h3 class="title1">Manage Staffs
				<button ng-hide="sctrl.saveStaff" class="btn btn-info" ng-click="sctrl.saveStaff = true">Add Staff</button>
			</h3>

			<div class="alert alert-success" ng-cloak ng-show="sctrl.successMessage">
				<strong>Success!</strong> <p ng-bind="sctrl.successMessage"></p>
			</div>

			<div class="alert alert-danger" ng-cloak ng-show="sctrl.errorMessage">
				<strong>Error!</strong> <p ng-bind="sctrl.errorMessage"></p>
			</div>

			<div class="panel panel-primary" ng-cloak ng-show="sctrl.saveStaff">
				<div class="panel-heading">
					<strong ng-cloak>{{sctrl.staff.staffId ? 'Update' : 'Add'}} Staff</strong>
					<button ng-click="sctrl.saveStaff = false" class="toggle-hide btn btn-default">
						<i class="glyphicon glyphicon-minus"></i>
					</button>
				</div>
				<div class="panel-body">
					<form ng-submit="sctrl.submit()" name="saveStaffForm">
						<div class="row">
							<div class="col-sm-4">
								<input type="hidden" ng-model="sctrl.staff.staffId"/>
								<div class="form-group">
									<label>Staff Name</label>
									<input required minLength="2" type="text" ng-model="sctrl.staff.staffName" class="form-control" />
								</div>
								<div class="form-group">
									<label>Address</label>
									<input required minLength="10" type="text" ng-model="sctrl.staff.address" class="form-control" />
								</div>
								<div class="form-group">
									<label>Phone Number</label>
									<input required type="text" minLength="10" maxLength="11" ng-model="sctrl.staff.phoneNumber" class="form-control" />
								</div>
								<div class="form-group">
									<label>Role</label>
									<select ng-model="sctrl.staff.role" required class="form-control">
										<option value="ROLE_ADMIN">ADMIN</option>
										<option value="ROLE_SALE">SALE</option>
										<option value="ROLE_SWITCHBOARD">SWITCHBOARD</option>
									</select>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label>Username</label>
									<input type="text" minLength="6" required ng-model="sctrl.staff.username" class="form-control" />
								</div>
								<div class="form-group" required ng-hide="sctrl.staff.staffId">
									<label>Password</label>
									<input type="password" minLenth="6"  ng-model="sctrl.staff.password" class="form-control" />
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label>Salary</label>
									<input type="number" required ng-model="sctrl.staff.salary" class="form-control" />
								</div>
								<div class="form-group">
									<label>Department</label>
									<select class="form-control" ng-model="sctrl.staff.departmentId" required
									ng-options="department.departmentId as department.departmentName for department in sctrl.listDepartments">
										
									</select>
								</div>
								<div class="form-group" ng-show="sctrl.staff.staffId">
									<label>Flags</label>
									<select required class="form-control" ng-model="sctrl.staff.flags"
									 ng-options="o.v as o.n for o in [{ n: 'False', v: false }, { n: 'True', v: true }]">
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<button class="btn btn-info" ng-cloak>{{sctrl.staff.staffId ? 'Update' : 'Add'}}</button>
								<button type="button" ng-click="sctrl.reset()" class="btn btn-warning">Reset</button>
								<button type="button" 
								ng-show = "sctrl.staff.staffId"
								ng-click="sctrl.reset(sctrl.staff.staffId)" 
								class="btn btn-warning">Reset Password</button>
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
									<button class="btn btn-default btn-sm" ng-disabled="sctrl.page === 1" ng-click="sctrl.decreasePage();">
										<i class="glyphicon glyphicon-menu-left"></i>
									</button>
								</div>

								<div class="form-group">
									<select ng-change="sctrl.changePage()" ng-options="n for n in sctrl.range(1, sctrl.listStaffs.totalPages, 1)" ng-model="sctrl.page"
									 class="form-control input-sm">
									</select>
								</div>


								<button class="btn btn-default btn-sm" ng-cloak>{{sctrl.page}}/{{sctrl.listStaffs.totalPages}}</button>


								<button class="btn btn-default btn-sm" ng-disabled="sctrl.page === sctrl.listStaffs.totalPages" ng-click="sctrl.increasePage(sctrl.listStaffs)">
									<i class="glyphicon glyphicon-menu-right"></i>
								</button>

								<div class="form-group">
									<label>Records per page : </label>
									<select ng-change="sctrl.changeRecordsPerPage()" ng-options="n for n in sctrl.range(1, 20, 1)" ng-model="sctrl.size" class="form-control input-sm">
									</select>
								</div>

							</div>
						</div>
						<div class="col-sm-5">
							<form name="searchStaffForm" class="form-inline">
								<div class="form-group">
									<select ng-model="sctrl.searchBy" required="true" class="form-control input-sm">
										<option value="staffId">Id</option>
										<option value="staffName">Staff Name</option>
										<option value="username">Username</option>
										<option value="address">Address</option>
										<option value="phoneNumber">Phone Number</option>
									</select>
								</div>
								<div class="input-group">
									<input type="text" placeholder="Search here..." required class="form-control input-sm" ng-model="sctrl.searchText" />
									<div class="input-group-btn">
										<button class="btn btn-default btn-sm" ng-click="sctrl.search()" ng-disabled="searchStaffForm.$invalid || searchStaffForm.$pristine"
										 type="submit">
											<i class="glyphicon glyphicon-search"></i>
										</button>
									</div>
								</div>
								<div class="form-group">
									<button class="btn btn-default btn-sm" ng-click="sctrl.reload()" type="button">
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
								<th>Role</th>
								<th>Username</th>
								<th>Salary</th>
								<th>DepartmentId</th>
								<th>Flags</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="s in sctrl.listStaffs.content">
								<th scope="row" ng-bind="sctrl.indexOrder($index)"></th>
								<td ng-bind="s.staffId"></td>
								<td ng-bind="s.staffName"></td>
								<td ng-bind="s.address"></td>
								<td ng-bind="s.phoneNumber"></td>
								<td ng-bind="s.role"></td>
								<td ng-bind="s.username"></td>
								<td ng-bind="s.salary"></td>
								<td ng-bind="s.departmentId"></td>
								<td ng-bind="s.flags"></td>
								<td>
									<a ng-click="sctrl.edit(s.staffId)" class="btn btn-danger"><i class="glyphicon glyphicon-edit"></i></a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>