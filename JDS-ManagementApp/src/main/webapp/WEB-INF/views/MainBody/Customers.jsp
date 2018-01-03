
<!-- main content start-->
<div id="page-wrapper" ng-controller="Customers as cctrl">
	<div class="main-page">
		<div class="tables">
			<h3 class="title1">Manage Customers
				<button ng-hide="cctrl.saveCustomer" class="btn btn-info" ng-click="cctrl.saveCustomer = true">Add Customer</button>
			</h3>

			<div class="alert alert-success" ng-cloak ng-show="cctrl.successMessage">
				<strong>Success!</strong> <p ng-bind="cctrl.successMessage"></p>
			</div>

			<div class="alert alert-danger" ng-cloak ng-show="cctrl.errorMessage">
				<strong>Error!</strong> <p ng-bind="cctrl.errorMessage"></p>
			</div>

			<div class="panel panel-primary" ng-cloak ng-show="cctrl.saveCustomer">
				<div class="panel-heading">
					<strong ng-cloak>{{cctrl.customer.customerId ? 'Update' : 'Add'}} Customer</strong>
					<button ng-click="cctrl.saveCustomer = false" class="toggle-hide btn btn-default">
						<i class="glyphicon glyphicon-minus"></i>
					</button>
				</div>
				<div class="panel-body">
					<form ng-submit="cctrl.submit()" name="saveCustomerForm">
						<div class="row">
							<div class="col-sm-4">
								<input type="hidden" ng-model="cctrl.customer.customerId"/>
								<div class="form-group">
									<label>Customer Name</label>
									<input required minLength="2" type="text" ng-model="cctrl.customer.customerName" class="form-control" />
								</div>
								<div class="form-group">
									<label>Address</label>
									<input type="text" required minLength="20" ng-model="cctrl.customer.address" class="form-control" />
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group" ng-hide="cctr.customer.customerId">
									<label>Phone Number</label>
									<input type="text" required 
									minLength="10"
									maxLength="11"
									ng-model="cctrl.customer.phoneNumber" class="form-control" />
								</div>
								<div class="form-group" ng-hide="cctrl.customer.customerId">
									<label>Password</label>
									<input required minLength="6" type="password" ng-model="cctrl.customer.password" class="form-control" />
								</div>
								<div class="form-group">
									<label>Customer Type</label>
									<select required class="form-control" ng-model="cctrl.customer.customerType">
										<option value="NORMAL">NORMAL</option>
										<option value="VIP">VIP</option>
									</select>
								</div>
								<div class="form-group" ng-show="cctrl.customer.customerId ">
									<label>Flags</label>
									<select 
									required class="form-control" ng-model="cctrl.customer.flags"
									 ng-options="o.v as o.n for o in [{ n: 'False', v: false }, { n: 'True', v: true }]">
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<button 
								ng-disabled="saveCustomerForm.$pristine"
								class="btn btn-info" ng-cloak>{{cctrl.customer.customerId ? 'Update' : 'Add'}}</button>
								<button type="button" ng-click="cctrl.reset()" class="btn btn-warning">Reset</button>
								<button type="button" ng-show="cctrl.customer.customerId" 
								ng-click="cctrl.resetPassword(cctrl.customer.customerId)" 
								class="btn btn-warning">
								Reset Password</button>
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
									<select ng-change="cctrl.changePage()" ng-options="n for n in cctrl.range(1, cctrl.listCustomers.totalPages, 1)" ng-model="cctrl.page"
									 class="form-control input-sm">
									</select>
								</div>


								<button class="btn btn-default btn-sm" ng-cloak>{{cctrl.page}}/{{cctrl.listCustomers.totalPages}}</button>


								<button class="btn btn-default btn-sm" ng-disabled="cctrl.page === cctrl.listCustomers.totalPages" ng-click="cctrl.increasePage(cctrl.listCustomers)">
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
							<form name="searchCustomerForm" class="form-inline">
								<div class="form-group">
									<select ng-model="cctrl.searchBy" required="true" class="form-control input-sm">
										<option value="phoneNumber">Phone Number</option>
										<option value="customerId">Customer Id</option>
										<option value="customerName">Customer Name</option>
										<option value="address">Address</option>
									</select>
								</div>
								<div class="input-group">
									<input type="text" placeholder="Search here..." required class="form-control input-sm" ng-model="cctrl.searchText" />
									<div class="input-group-btn">
										<button class="btn btn-default btn-sm" ng-click="cctrl.search()" ng-disabled="searchCustomerForm.$invalid || searchCustomerForm.$pristine"
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
								<th>Address</th>
								<th>Phone Number</th>
								<th>Customer Type</th>
								<th>Date Created</th>
								<th>Flags</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="c in cctrl.listCustomers.content">
								<th scope="row" ng-bind="cctrl.indexOrder($index)"></th>
								<td ng-bind="c.customerId"></td>
								<td ng-bind="c.customerName"></td>
								<td ng-bind="c.address"></td>
								<td ng-bind="c.phoneNumber"></td>
								<td ng-bind="c.customerType"></td>
								<td ng-bind="c.dateCreated"></td>
								<td ng-bind="c.flags"></td>
								<td>
									<a ng-click="cctrl.edit(c.customerId)" class="btn btn-danger"><i class="glyphicon glyphicon-edit"></i></a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>