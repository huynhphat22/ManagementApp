<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- main content start-->
<div id="page-wrapper" ng-controller="MonthCosts as dctrl">
	<div class="main-page">
		<div class="tables">
			<h3 class="title1">MonthCost For Department
				<button ng-hide="dctrl.saveMonthCost" class="btn btn-info" ng-click="dctrl.saveMonthCost = true;dctrl.isUpdate = false ">Add Food</button>
			</h3>

			<div class="alert alert-success" ng-cloak ng-show="dctrl.successMessage">
				<strong>Success!</strong> {{dctrl.successMessage}}
			</div>

			<div class="alert alert-danger" ng-cloak ng-show="dctrl.errorMessage">
				<strong>Error!</strong> {{dctrl.errorMessage}}.
			</div>


			<div class="panel panel-primary" ng-cloak ng-show="dctrl.saveMonthCost">
				<div class="panel-heading">
					<strong>{{dctrl.isUpdate ? 'Update Food For' : 'Add Food To'}}  MonthCost</strong>
					<button ng-click="dctrl.saveMonthCost = false" class="toggle-hide btn btn-default">
						<i class="glyphicon glyphicon-minus"></i>
					</button>
				</div>
				<div class="panel-body">
					<form ng-submit="dctrl.submit()" name="monthCostForm">
						<div class="row">
							<input type="hidden" ng-model="dctrl.monthCost.monthCostId"/>
							<div class="col-sm-4">
								<div class="form-group">
									<label>Type Of Cost</label>
									<input required ng-model="dctrl.monthCost.typeOfCost" class="form-control" />
								</div>
								
								<div class="form-group">
									<label>Note</label>
									<input type="text" required ng-model="dctrl.monthCost.note" class="form-control" />
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label>Price</label>
									<input type="number" min="10000" ng-model="dctrl.monthCost.price" class="form-control" />
								</div>
								
								<div class="form-group" ng-show="dctrl.monthCost.monthCostId">
									<label>Flags</label>
									<select required class="form-control" ng-model="dctrl.monthCost.flags"
									 ng-options="o.v as o.n for o in [{ n: 'False', v: false }, { n: 'True', v: true }]">
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<button class="btn btn-info" ng-cloak>{{dctrl.monthCost.monthCostId ? 'Update' : 'Add'}}</button>
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
									<select ng-change="dctrl.changePage()" ng-options="n for n in dctrl.range(1, dctrl.listMonthCosts.totalPages, 1)" ng-model="dctrl.page"
									 class="form-control input-sm">
									</select>
								</div>


								<button class="btn btn-default btn-sm" ng-cloak>{{dctrl.page}}/{{dctrl.listMonthCosts.totalPages}}</button>


								<button class="btn btn-default btn-sm" ng-disabled="dctrl.page === dctrl.listMonthCosts.totalPages" ng-click="dctrl.increasePage(dctrl.listMonthCosts)">
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
							<form name="searchMonthCostForm" class="form-inline">
								<div class="form-group">
									<select ng-model="dctrl.searchBy" required="true" class="form-control input-sm">
										<option value="monthOfCost">Month Of Cost</option>
										<option value="price">Price</option>
										<option value="typeOfCost">Type Of Cost</option>
									</select>
								</div>
								<div class="input-group">
									<input type="text" placeholder="Search here..." required class="form-control input-sm" ng-model="dctrl.searchText" />
									<div class="input-group-btn">
										<button class="btn btn-default btn-sm" ng-click="dctrl.search()" ng-disabled="searchMonthCostForm.$invalid || searchMonthCostForm.$pristine"
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
								<th>Month Of Cost</th>
								<th>Type Of Cost</th>
								<th>Price</th>
								<th>Note</th>								
								<th>Date Created</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-cloak ng-repeat="d in dctrl.listMonthCosts.content">
								<th scope="row">{{dctrl.indexOrder($index)}}</th>
								<td>{{d.monthOfCost}}</td>
								<td>{{d.typeOfCost}}</td>
								<td>{{d.price}}</td>
								<td>{{d.note}}</td>
								<td>{{d.dateCreated}}</td>
								<td>
									<a ng-click="dctrl.edit(d.monthCostId)" class="btn btn-danger"><i class="glyphicon glyphicon-edit"></i></a>
								</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>