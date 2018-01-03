
<!-- main content start-->
<div id="page-wrapper" ng-controller="Tables as tctrl">
	<div class="main-page">
		<div class="tables">
			<h3 class="title1">Manage Tables
				<button ng-hide="tctrl.saveTable" class="btn btn-info" ng-click="tctrl.saveTable = true">Add Table</button>
			</h3>

			<div class="alert alert-success" ng-cloak ng-show="tctrl.successMessage">
				<strong>Success!</strong> <p ng-bind="tctrl.successMessage"></p>
			</div>

			<div class="alert alert-danger" ng-cloak ng-show="tctrl.errorMessage">
				<strong>Error!</strong> <p ng-bind="tctrl.errorMessage"></p>
			</div>

			<div class="panel panel-primary" ng-cloak ng-show="tctrl.saveTable">
				<div class="panel-heading">
					<strong ng-cloak>{{tctrl.table.tableId ? 'Update' : 'Add'}} Table</strong>
					<button ng-click="tctrl.saveTable = false" class="toggle-hide btn btn-default">
						<i class="glyphicon glyphicon-minus"></i>
					</button>
				</div>
				<div class="panel-body">
					<form ng-submit="tctrl.submit()" name="saveTableForm">
						<div class="row">
							<div class="col-sm-4">
								<input type="hidden" ng-model="tctrl.table.tableId"/>
								<div class="form-group">
									<label>Table Number</label>
									<input type="number" required min="1" ng-model="tctrl.table.tableNumber" class="form-control" />
								</div>
								<div class="form-group">
									<label>Status</label>
									<select class="form-control" required ng-model="tctrl.table.tableStatus">
										<option value="empty">Empty</option>
										<option value="ordered">Ordered</option>
									</select>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group" ng-show="tctrl.table.tableId ">
									<label>Flags</label>
									<select 
									required class="form-control" ng-model="tctrl.table.flags"
									 ng-options="o.v as o.n for o in [{ n: 'False', v: false }, { n: 'True', v: true }]">
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<button class="btn btn-info" ng-disabled="saveTableForm.$pristine" ng-cloak>{{tctrl.table.tableId ? 'Update' : 'Add'}}</button>
								<button type="button" ng-click="tctrl.reset()" class="btn btn-warning">Reset</button>
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
									<button class="btn btn-default btn-sm" ng-disabled="tctrl.page === 1" ng-click="tctrl.decreasePage();">
										<i class="glyphicon glyphicon-menu-left"></i>
									</button>
								</div>

								<div class="form-group">
									<select ng-change="tctrl.changePage()" ng-options="n for n in tctrl.range(1, tctrl.listTables.totalPages, 1)" ng-model="tctrl.page"
									 class="form-control input-sm">
									</select>
								</div>


								<button class="btn btn-default btn-sm" ng-cloak>{{tctrl.page}}/{{tctrl.listTables.totalPages}}</button>


								<button class="btn btn-default btn-sm" ng-disabled="tctrl.page === tctrl.listTables.totalPages" ng-click="tctrl.increasePage(tctrl.listTables)">
									<i class="glyphicon glyphicon-menu-right"></i>
								</button>

								<div class="form-group">
									<label>Records per page : </label>
									<select ng-change="tctrl.changeRecordsPerPage()" ng-options="n for n in tctrl.range(1, 20, 1)" ng-model="tctrl.size" class="form-control input-sm">
									</select>
								</div>

							</div>
						</div>
						<div class="col-sm-5">
							<form name="searchTableForm" class="form-inline">
								<div class="form-group">
									<select ng-model="tctrl.searchBy" required="true" class="form-control input-sm">
										<option value="tableId">Table Id</option>
										<option value="tableNumber">Table Number</option>
										<option value="tableStatus">Table Status</option>
									</select>
								</div>
								<div class="input-group">
									<input type="text" placeholder="Search here..." required class="form-control input-sm" ng-model="tctrl.searchText" />
									<div class="input-group-btn">
										<button class="btn btn-default btn-sm" ng-click="tctrl.search()" ng-disabled="searchTableForm.$invalid || searchTableForm.$pristine"
										 type="submit">
											<i class="glyphicon glyphicon-search"></i>
										</button>
									</div>
								</div>
								<div class="form-group">
									<button class="btn btn-default btn-sm" ng-click="tctrl.reload()" type="button">
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
								<th>Number</th>
								<th>Status</th>
								<th>DeptId</th>
								<th>Flags</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="t in tctrl.listTables.content">
								<th scope="row" ng-bind="tctrl.indexOrder($index)"></th>
								<td ng-bind="t.tableId"></td>
								<td ng-bind="t.tableNumber"></td>
								<td ng-bind="t.tableStatus"></td>
								<td ng-bind="t.departmentId"></td>
								<td ng-bind="t.flags"></td>
								<td>
									<a ng-click="tctrl.edit(t.tableId)" class="btn btn-danger"><i class="glyphicon glyphicon-edit"></i></a>
								</td>
								
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>