<#import "/spring.ftl" as spring />
<#import "layout.ftl" as layout>

<@layout.myLayout>

<#if alarmSaved??>
    <div class="alert alert-success" role="alert">
        Alarm saved succesfully!
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</#if>
<table class="table table-striped">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Symbol</th>
        <th scope="col">Price</th>
        <th scope="col">Change</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

</@layout.myLayout>