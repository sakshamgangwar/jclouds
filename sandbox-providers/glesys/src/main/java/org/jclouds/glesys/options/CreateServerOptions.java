/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.glesys.options;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.Iterables.find;

import java.util.Map;

import org.jclouds.glesys.domain.ServerSpec;
import org.jclouds.http.HttpRequest;
import org.jclouds.io.payloads.UrlEncodedFormPayload;
import org.jclouds.rest.MapBinder;
import org.jclouds.rest.internal.GeneratedHttpRequest;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimaps;

/**
 * @author Adam Lowe
 */
public class CreateServerOptions implements MapBinder {

   private String ip;
   private String description;

   @Override
   public <R extends HttpRequest> R bindToRequest(R request, Map<String, String> postParams) {
      checkArgument(checkNotNull(request, "request") instanceof GeneratedHttpRequest<?>,
            "this binder is only valid for GeneratedHttpRequests!");
      GeneratedHttpRequest<?> gRequest = (GeneratedHttpRequest<?>) request;
      checkState(gRequest.getArgs() != null, "args should be initialized at this point");

      ImmutableMultimap.Builder<String, String> formParams = ImmutableMultimap.<String, String> builder();
      formParams.putAll(Multimaps.forMap(postParams));
      ServerSpec serverSpec = ServerSpec.class.cast(find(gRequest.getArgs(), instanceOf(ServerSpec.class)));
      formParams.put("datacenter", serverSpec.getDatacenter());
      formParams.put("platform", serverSpec.getPlatform());
      formParams.put("templatename", serverSpec.getTemplateName());
      formParams.put("disksize", serverSpec.getDiskSizeGB() + "");
      formParams.put("memorysize", serverSpec.getMemorySizeMB() + "");
      formParams.put("cpucores", serverSpec.getCpuCores() + "");
      formParams.put("transfer", serverSpec.getTransferGB() + "");
      if (ip != null)
         formParams.put("ip", ip);
      if (description != null)
         formParams.put("description", description);

      request.setPayload(new UrlEncodedFormPayload(formParams.build()));
      return request;
   }

   public static class Builder {
      /**
       * @see CreateServerOptions#description
       */
      public static CreateServerOptions description(String primaryNameServer) {
         CreateServerOptions options = new CreateServerOptions();
         return options.description(primaryNameServer);
      }

      /**
       * @see CreateServerOptions#ip
       */
      public static CreateServerOptions ip(String ip) {
         CreateServerOptions options = new CreateServerOptions();
         return options.ip(ip);
      }
   }

   /**
    * @param description
    *           the description of the server
    */
   public CreateServerOptions description(String description) {
      this.description = description;
      return this;
   }

   /**
    * @param ip
    *           the ip address to assign to the server
    */
   public CreateServerOptions ip(String ip) {
      this.ip = ip;
      return this;
   }

   @Override
   public <R extends HttpRequest> R bindToRequest(R request, Object input) {
      throw new IllegalArgumentException();
   }
}
