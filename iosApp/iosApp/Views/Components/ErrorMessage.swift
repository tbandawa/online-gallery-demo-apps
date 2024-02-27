//
//  ErrorMessage.swift
//  Online Gallery
//
//  Created by Tendai Bandawa on 2024/02/26.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ErrorMessage: View {
    
    var title: String
    var messages: [String]
    var dismissMessage: () -> Void
    
    var body: some View {
        VStack {
            VStack {
                HStack(spacing: 30) {
                    Text("\(title)")
                        .foregroundColor(Color("errorColor"))
                        .font(
                            .system(size: 18)
                            .weight(.heavy)
                        )
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Image("icon-close")
                        .resizable()
                        .scaledToFit()
                        .foregroundColor(Color("errorColor"))
                        .frame(width: 30, height: 30)
                        .onTapGesture {
                            dismissMessage()
                        }
                }
                .padding(.horizontal, 10)
                .frame(height: 45)
                Spacer()
                    .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: 2)
                
                ForEach(messages, id: \.self) { message in
                    Text("\(message)")
                        .foregroundColor(Color("errorColor"))
                        .font(
                            .system(size: 16)
                            .weight(.regular)
                        )
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .padding(.horizontal, 10)
                    Spacer()
                        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: 10)
                }
                
            }
            .frame(maxWidth: .infinity)
            .background(Color("errorBackground"))
            .cornerRadius(8)
            .shadow(color: Color.gray, radius: 4, x: 0.5, y: 1.5)
        }
        .frame(maxWidth: .infinity)
        .padding(16)
        .background(Color.white)
        .cornerRadius(0)
    }
}

#Preview {
    ErrorMessage(
        title: "Message Title",
        messages: ["Messages one", "Message two"],
        dismissMessage: { print("dissmiss clicked") }
    )
}
