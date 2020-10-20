<#import "/spring.ftl" as spring />
<#import  "layout.ftl" as layout >

<@layout.myLayout>

    <#if alarmSaved??>
        <div class="alert alert-success" role="alert">
            Alarm saved successfully !
            <button class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </#if>

    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Initial Price</th>
            <th scope="col">Current Price</th>
            <th scope="col">Variance</th>
            <th scope="col">Target variance</th>
            <th scope="col">Target price</th>
            <th></th>
            <th></th>

        </tr>
        </thead>
        <tbody>
        <#list alarms as alarm>
            <tr>
                <th scope="row">${alarm.crypto.name!'N/A'}</th>
                <td>${alarm.referencePrice!'N/A'}</td>
                <td>${alarm.crypto.price!'N/A'}</td>
                <td>${alarm.variance!'N/A'}</td>
                <td>${alarm.rule!'N/A'}</td>
                <td>${alarm.alarmPrice!'N/A'}</td>

                <td>
                    <button type="button" class="btn btn-info" data-toggle="modal"
                            data-target="#editAlarmModal" data-crypto-name="${alarm.crypto.name}" data-initial-price="${alarm.referencePrice}"
                            data-current-price="${alarm.crypto.price}" data-rule="${alarm.rule}" data-variance="${alarm.variance}"
                            data-id="${alarm.id}">
                        Edit
                    </button>
                </td>
                <td>
                    <button type="button" class="btn btn-info" data-toggle="modal"
                            data-target="#deleteAlarmModal" data-crypto-name="${alarm.crypto.name}" data-initial-price="${alarm.referencePrice}"
                            data-current-price="${alarm.crypto.price}" data-rule="${alarm.rule}" data-variance="${alarm.variance}"
                            data-id="${alarm.id}">
                        Delete
                    </button>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

    <!--Modal-->
    <div class="modal fade" id="editAlarmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"> Edit Alarm </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="add-alarm-form" method="POST" action="alarms">

<#--                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->

                        <div>
                            <h4>Symbol</h4>
                            <p id="cryptoInfo"> BAT </p>
                        </div>

                        <div>
                            <h4>Initial price</h4>
                            <p id="initialPriceInfo">20.3</p>
                        </div>

                        <div>
                            <h4>Current Price</h4>
                            <p id="varianceInfo"> 22.3</p>
                        </div>

                        <div>
                            <h4>Variance</h4>
                            <p id="varianceInfo">22.3</p>
                        </div>

                        <h4>Target Variance</h4>

                        <input id="identifier" type="text" name="id" value="1">
                        <input id="cryptoName" type="text" name="cryptoName" value="Ripple" hidden>
                        <input id="rule" class="form-control" type="text" placeholder="Rule" name="rule" value="+20">

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"> Close </button>
                    <button type="submit" class="btn btn-primary" form="add-alarm-form"> Save alarm</button>
                </div>
            </div>
        </div>
    </div>

    <!--Modal-->
    <div class="modal fade" id="deleteAlarmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Delete Alarm</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <h4 class="text-danger"> Are you sure you want to delete the following alarm?</h4>
                    <br>
                    <form id="add-alarm-form">
                        <div>
                            <h4>Crypto Name</h4>
                            <p id="cryptoInfo"> BAT </p>
                        </div>

                        <div>
                            <h4>Initial price</h4>
                            <p id="initialPriceInfo">20.3</p>
                        </div>

                        <div>
                            <h4>Current Price</h4>
                            <p id="currentPriceInfo"> 22.3</p>
                        </div>

                        <div>
                            <h4>Variance</h4>
                            <p id="varianceInfo">22.3</p>
                        </div>

                        <div>
                            <h5> Target Variance</h5>
                            <p id="rule">22.3</p>
                        </div>

                        <input type="number" id="identifier" name="id" value="6" hidden>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"> Close </button>
                    <button type="button" id="delete-alarm" class="btn btn-danger" form="add-alarm-form"> Delete alarm</button>
                </div>
            </div>

        </div>

    </div>

    <script>
        $('#editAlarmModal').on('show.bs.modal', function (event) {

            var button = $(event.relatedTarget); // Button that triggered the modal
            var cryptoName = button.data('crypto-name'); // Extract info from data-* attributes
            var initialPrice = button.data('initial-price');
            var currentPrice = button.data('current-price');
            var variance = button.data('variance');
            var rule = button.data('rule');
            var id = button.data('id');

            var modal = $(this);

            modal.find('.modal-title').text('Edit alarm for ' + cryptoName);
            modal.find('#identifier').val(id);
            modal.find('#cryptoName').val(cryptoName);
            modal.find('#rule').val(rule);
            modal.find('#cryptoInfo').text(cryptoName);
            modal.find('#initialPriceInfo').text(initialPrice);
            modal.find('#currentPriceInfo').text(currentPrice);
            modal.find('#varianceInfo').text(variance);
        });

        $('#deleteAlarmModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Button that triggered the modal
            var cryptoName = button.data('crypto-name'); // Extract info from data-* attributes
            var initialPrice = button.data('initial-price');
            var currentPrice = button.data('current-price');
            var variance = button.data('variance');
            var rule = button.data('rule');
            var id = button.data('id');

            var modal = $(this);

            modal.find('.modal-title').text('Delete alarm for ' + cryptoName);
            modal.find('#identifier').val(id);
            modal.find('#rule').text(rule);
            modal.find('#cryptoName').text(cryptoName);
            modal.find('#cryptoInfo').text(cryptoName);
            modal.find('#initialPriceInfo').text(initialPrice);
            modal.find('#currentPriceInfo').text(currentPrice);
            modal.find('#varianceInfo').text(variance);

            modal.find('#delete-alarm').data('id', id);
        });

        $('#delete-alarm').click(function(){
            // var button = $(event.relatedTarget);
            var id = $(this).data('id');
            console.log("id", id);
            $.ajax({
                success: function(){
                    window.location.reload();
                },
                error: function(err){
                    console.log("Error deleting alarm!", err);
                },
                processData: false,
                type: 'DELETE',
                url: '/alarms/' + id
            });
        })
    </script>

</@layout.myLayout>