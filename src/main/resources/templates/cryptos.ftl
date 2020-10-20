<#import "/spring.ftl" as spring />
<#import "layout.ftl" as layout>
<#import "util-macros.ftl" as util>

<@layout.myLayout>

    <@util.alarmSaving></@util.alarmSaving>

    <form id="add-crypto-to-monitor-form" method="post" action="cryptos/monitor">
        <div class="input-group">
            <input id="monitor-crypto-name" class="form-control" type="text" name="name" autocomplete="off"
                   placeholder="Search for cryptos to monitor...">
            <div class="input-group-append">
                <button type="submit" form="add-crypto-to-monitor-form" class="btn btn-primary">Monitor Crypto</button>
            </div>

        </div>
        <input id="monitor-crypto-symbol-hidden" type="hidden" name="symbol">
    </form>

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
        <#list cryptos as crypto>
            <#if crypto.cryptoName??>
                <tr>
                    <th scope="row">${crypto.name!'N/A'}</th>
                    <td>$${crypto.price!'N/A'}</td>
                    <td>${crypto.changePercent!'N/A'}%</td>
                    <td>
                        <#assign changePercentNumber = crypto.changePercent?remove_ending("%")?number>
                        <#if changePercentNumber gt 0>
                            <p style="color: green; font-size: 1.3em; font-weight: bold">&#8593;</p>
                        <#elseif changePercentNumber == 0>
                            ---
                        <#else>
                            <p style="color: red; font-size: 1.3em; font-weight: bold">&#8595;</p>
                        </#if>

                    </td>
                    <td>
                        <button type="button" class="btn btn-info" data-toggle="modal"
                                data-target="#addAlarmModal" data-symbol="${crypto.name}">
                            Add alarm
                        </button>
                    </td>
                </tr>
            </#if>
        </#list>
        </tbody>
    </table>

    <script>

        $('#monitor-crypto-name').typeahead({
            ajax: '/search',
            valueField: 'symbol',
            displayField: 'name',
            onSelect: function (arg) {
                $('#monitor-crypto-symbol-hidden').val(arg.value);
            }
        });

    </script>

</@layout.myLayout>