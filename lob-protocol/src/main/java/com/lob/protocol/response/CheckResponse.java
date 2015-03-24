package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.id.CheckId;
import org.joda.money.Money;
import org.joda.time.DateTime;

import java.util.Collection;
import java.util.List;

import static com.lob.Util.defensiveCopy;

public class CheckResponse {
    @JsonProperty private final CheckId id;
    @JsonProperty private final String name;
    @JsonProperty private final int checkNumber;
    @JsonProperty private final String memo;
    @JsonProperty private final Money amount;
    @JsonProperty private final AddressResponse to;
    @JsonProperty private final BankAccountResponse backAccount;
    @JsonProperty private final String status;
    @JsonProperty private final String message;
    @JsonProperty private final Money price;
    @JsonProperty private final String url;
    @JsonProperty private final TrackingResponse tracking;
    @JsonProperty private final DateTime dateCreated;
    @JsonProperty private final DateTime dateModified;
    @JsonProperty private final DateTime expectedDeliveryDate;
    @JsonProperty private final Collection<ThumbnailResponse> thumbnails;
    @JsonProperty private final String object;

    @JsonCreator
    public CheckResponse(
            @JsonProperty("id") final CheckId id,
            @JsonProperty("name") final String name,
            @JsonProperty("check_number") final int checkNumber,
            @JsonProperty("memo") final String memo,
            @JsonProperty("amount") final Money amount,
            @JsonProperty("to") final AddressResponse to,
            @JsonProperty("bank_account") final BankAccountResponse backAccount,
            @JsonProperty("status") final String status,
            @JsonProperty("message") final String message,
            @JsonProperty("price") final Money price,
            @JsonProperty("url") final String url,
            @JsonProperty("tracking") final TrackingResponse tracking,
            @JsonProperty("date_created") final DateTime dateCreated,
            @JsonProperty("date_modified") final DateTime dateModified,
            @JsonProperty("expected_delivery_date") final DateTime expectedDeliveryDate,
            @JsonProperty("thumbnails") final Collection<ThumbnailResponse> thumbnails,
            @JsonProperty("object") final String object) {
        this.id = id;
        this.name = name;
        this.checkNumber = checkNumber;
        this.memo = memo;
        this.amount = amount;
        this.to = to;
        this.backAccount = backAccount;
        this.status = status;
        this.message = message;
        this.price = price;
        this.url = url;
        this.tracking = tracking;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.thumbnails = thumbnails;
        this.object = object;
    }

    public CheckId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCheckNumber() {
        return checkNumber;
    }

    public String getMemo() {
        return memo;
    }

    public Money getAmount() {
        return amount;
    }

    public AddressResponse getTo() {
        return to;
    }

    public BankAccountResponse getBackAccount() {
        return backAccount;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Money getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public TrackingResponse getTracking() {
        return tracking;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public DateTime getDateModified() {
        return dateModified;
    }

    public DateTime getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public Collection<ThumbnailResponse> getThumbnails() {
        return defensiveCopy(this.thumbnails);
    }

    public String getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "CheckResponse{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", checkNumber=" + checkNumber +
            ", memo='" + memo + '\'' +
            ", amount=" + amount +
            ", to=" + to +
            ", backAccount=" + backAccount +
            ", status='" + status + '\'' +
            ", message='" + message + '\'' +
            ", price=" + price +
            ", url='" + url + '\'' +
            ", tracking=" + tracking +
            ", dateCreated=" + dateCreated +
            ", dateModified=" + dateModified +
            ", expectedDeliveryDate=" + expectedDeliveryDate +
            ", thumbnails=" + thumbnails +
            ", object='" + object + '\'' +
            '}';
    }
}
